package com.giordanobrunomichela.final_test.service;

import com.giordanobrunomichela.final_test.dto.ReportDTO;
import com.giordanobrunomichela.final_test.model.PhoneNumber;
import com.giordanobrunomichela.final_test.model.PhoneNumberStatus;
import com.giordanobrunomichela.final_test.model.Report;
import com.giordanobrunomichela.final_test.model.ReportType;
import com.giordanobrunomichela.final_test.repository.PhoneNumberRepository;
import com.giordanobrunomichela.final_test.repository.ReportRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReportService {
    private final ReportRepository reportRepository;
    private final PhoneNumberRepository phoneNumberRepository;

    public ReportService(ReportRepository reportRepository, PhoneNumberRepository phoneNumberRepository) {
        this.reportRepository = reportRepository;
        this.phoneNumberRepository = phoneNumberRepository;
    }

    public List<ReportDTO> getAllReports() {
        return reportRepository.findAll().stream()/*  */
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ReportDTO getReportById(Long id) {
        return reportRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    private void updatePhoneNumberStatus(PhoneNumber phoneNumber) {
        if ((phoneNumber.getSpamReportCount() - phoneNumber.getNoSpamReportCount()) > 3) {
            phoneNumber.setPhoneNumberStatus(PhoneNumberStatus.SPAM);
        } else if ((phoneNumber.getSpamReportCount() - phoneNumber.getNoSpamReportCount()) <= 3) {
            phoneNumber.setPhoneNumberStatus(PhoneNumberStatus.NOSPAM);
        }
    }

    public ReportDTO createReport(ReportDTO reportDTO) {
        Report report = convertToEntity(reportDTO);
        report.setReportDate(new Date());

        if (report.getPhoneNumber() != null) {
            Optional<PhoneNumber> existingPhoneNumber = phoneNumberRepository
                    .findByPhoneNumber(report.getPhoneNumber());

            PhoneNumber phoneNumber;
            if (existingPhoneNumber.isEmpty()) {
                phoneNumber = new PhoneNumber();
                phoneNumber.setPhoneNumber(report.getPhoneNumber());
                phoneNumber.setDescription(report.getDescription());
                phoneNumber.setPhoneNumberStatus(PhoneNumberStatus.NOSPAM);

                if (report.getReportType() == ReportType.SPAM) {
                    phoneNumber.setSpamReportCount(1);
                    phoneNumber.setNoSpamReportCount(0);
                } else {
                    phoneNumber.setSpamReportCount(0);
                    phoneNumber.setNoSpamReportCount(1);
                }

                updatePhoneNumberStatus(phoneNumber);
                phoneNumber = phoneNumberRepository.save(phoneNumber);
            } else {
                phoneNumber = existingPhoneNumber.get();

                if (report.getReportType() == ReportType.SPAM) {
                    phoneNumber.setSpamReportCount(phoneNumber.getSpamReportCount() + 1);
                    updatePhoneNumberStatus(phoneNumber);
                    phoneNumber = phoneNumberRepository.save(phoneNumber);
                } else if (report.getReportType() == ReportType.NOSPAM) {
                    phoneNumber.setNoSpamReportCount(phoneNumber.getNoSpamReportCount() + 1);
                    updatePhoneNumberStatus(phoneNumber);
                    phoneNumber = phoneNumberRepository.save(phoneNumber);
                }
            }

            report.setPhoneNumberId(phoneNumber.getId());
        }

        return convertToDTO(reportRepository.save(report));
    }

    public ReportDTO updateReport(Long id, ReportDTO dto) {
        Optional<Report> existingReportOpt = reportRepository.findById(id);
        if (existingReportOpt.isPresent()) {
            Report report = existingReportOpt.get();

            if (dto.getReportDate() != null) {
                report.setReportDate(dto.getReportDate());
            }
            if (dto.getDescription() != null) {
                report.setDescription(dto.getDescription());
            }
            if (dto.getReportType() != null) {
                report.setReportType(dto.getReportType());
            }

            return convertToDTO(reportRepository.save(report));
        }
        return null;
    }

    public void deleteReport(Long id) {
        Optional<Report> reportOpt = reportRepository.findById(id);
        if (reportOpt.isPresent()) {
            Report report = reportOpt.get();

            if (report.getPhoneNumberId() != null) {
                Optional<PhoneNumber> phoneNumberOpt = phoneNumberRepository.findById(report.getPhoneNumberId());
                if (phoneNumberOpt.isPresent()) {
                    PhoneNumber phoneNumber = phoneNumberOpt.get();

                    if (report.getReportType() == ReportType.SPAM) {
                        int currentCount = phoneNumber.getSpamReportCount();
                        if (currentCount > 0) {
                            phoneNumber.setSpamReportCount(currentCount - 1);
                            updatePhoneNumberStatus(phoneNumber);
                            phoneNumberRepository.save(phoneNumber);
                        }
                    } else if (report.getReportType() == ReportType.NOSPAM) {
                        int currentCount = phoneNumber.getNoSpamReportCount();
                        if (currentCount > 0) {
                            phoneNumber.setNoSpamReportCount(currentCount - 1);
                            updatePhoneNumberStatus(phoneNumber);
                            phoneNumberRepository.save(phoneNumber);
                        }
                    }
                }
            }

            reportRepository.deleteById(id);
        }
    }

    public ReportDTO convertToDTO(Report report) {
        ReportDTO dto = new ReportDTO();
        dto.setId(report.getId());
        dto.setReportDate(report.getReportDate());
        dto.setDescription(report.getDescription());
        dto.setReportType(report.getReportType());
        dto.setPhoneNumber(report.getPhoneNumber());
        return dto;
    }

    public Report convertToEntity(ReportDTO dto) {
        Report report = new Report();
        report.setId(dto.getId());
        report.setReportDate(dto.getReportDate());
        report.setDescription(dto.getDescription());
        report.setReportType(dto.getReportType());
        report.setPhoneNumber(dto.getPhoneNumber());
        return report;
    }
}