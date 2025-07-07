package com.giordanobrunomichela.final_test.service;

import com.giordanobrunomichela.final_test.dto.PhoneNumberDTO;
import com.giordanobrunomichela.final_test.model.PhoneNumber;
import com.giordanobrunomichela.final_test.repository.PhoneNumberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PhoneNumberService {
    private final PhoneNumberRepository phoneNumberRepository;

    public PhoneNumberService(PhoneNumberRepository phoneNumberRepository) {
        this.phoneNumberRepository = phoneNumberRepository;
    }

    public List<PhoneNumberDTO> getAllPhoneNumbers() {
        return phoneNumberRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public PhoneNumberDTO getPhoneNumberById(Long id) {
        return phoneNumberRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public PhoneNumberDTO createPhoneNumber(PhoneNumberDTO phoneNumberDTO) {
        PhoneNumber phoneNumber = convertToEntity(phoneNumberDTO);
        return convertToDTO(phoneNumberRepository.save(phoneNumber));
    }

    public PhoneNumberDTO updatePhoneNumber(Long id, PhoneNumberDTO dto) {
        Optional<PhoneNumber> existingPhoneNumberOpt = phoneNumberRepository.findById(id);
        if (existingPhoneNumberOpt.isPresent()) {
            PhoneNumber phoneNumber = existingPhoneNumberOpt.get();

            if (dto.getDescription() != null) {
                phoneNumber.setDescription(dto.getDescription());
            }
            if (dto.getPhoneNumber() != null) {
                phoneNumber.setPhoneNumber(dto.getPhoneNumber());
            }
            if (dto.getSpamReportCount() != null) {
                phoneNumber.setSpamReportCount(dto.getSpamReportCount());
            }
            if (dto.getPhoneNumberStatus() != null) {
                phoneNumber.setPhoneNumberStatus(dto.getPhoneNumberStatus());
            }

            return convertToDTO(phoneNumberRepository.save(phoneNumber));
        }
        return null;
    }

    public void deletePhoneNumber(Long id) {
        phoneNumberRepository.deleteById(id);
    }

    public PhoneNumberDTO convertToDTO(PhoneNumber phoneNumber) {
        PhoneNumberDTO dto = new PhoneNumberDTO();
        dto.setId(phoneNumber.getId());
        dto.setDescription(phoneNumber.getDescription());
        dto.setPhoneNumber(phoneNumber.getPhoneNumber());
        dto.setSpamReportCount(phoneNumber.getSpamReportCount());
        dto.setPhoneNumberStatus(phoneNumber.getPhoneNumberStatus());
        return dto;
    }

    public PhoneNumber convertToEntity(PhoneNumberDTO dto) {
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setId(dto.getId());
        phoneNumber.setDescription(dto.getDescription());
        phoneNumber.setPhoneNumber(dto.getPhoneNumber());
        phoneNumber.setSpamReportCount(dto.getSpamReportCount());
        phoneNumber.setPhoneNumberStatus(dto.getPhoneNumberStatus());
        return phoneNumber;
    }
}
