package com.giordanobrunomichela.final_test.service;

import com.giordanobrunomichela.final_test.dto.ReportDTO;
import com.giordanobrunomichela.final_test.model.Report;
import com.giordanobrunomichela.final_test.repository.ReportRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReportService {
    private final ReportRepository reportRepository;

    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public List<ReportDTO> getAllReports() {
        return reportRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ReportDTO getReportById(Long id) {
        return reportRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public ReportDTO createReport(ReportDTO reportDTO) {
        Report report = convertToEntity(reportDTO);
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
        reportRepository.deleteById(id);
    }

    public ReportDTO convertToDTO(Report report) {
        ReportDTO dto = new ReportDTO();
        dto.setId(report.getId());
        dto.setReportDate(report.getReportDate());
        dto.setDescription(report.getDescription());
        dto.setReportType(report.getReportType());
        return dto;
    }

    public Report convertToEntity(ReportDTO dto) {
        Report report = new Report();
        report.setId(dto.getId());
        report.setReportDate(dto.getReportDate());
        report.setDescription(dto.getDescription());
        report.setReportType(dto.getReportType());
        return report;
    }
}