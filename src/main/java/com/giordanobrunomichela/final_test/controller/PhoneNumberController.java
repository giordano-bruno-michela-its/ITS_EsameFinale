package com.giordanobrunomichela.final_test.controller;

import com.giordanobrunomichela.final_test.dto.PhoneNumberDTO;
import com.giordanobrunomichela.final_test.service.PhoneNumberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/phonenumbers")
@CrossOrigin(origins = "http://localhost:3000")
public class PhoneNumberController {

    private final PhoneNumberService phoneNumberService;

    public PhoneNumberController(PhoneNumberService phoneNumberService) {
        this.phoneNumberService = phoneNumberService;
    }

    @GetMapping("/all")
    public List<PhoneNumberDTO> getAllPhoneNumbers() {
        return phoneNumberService.getAllPhoneNumbers();
    }

    @GetMapping("/spam")
    public List<PhoneNumberDTO> getSpamPhoneNumbers() {
        return phoneNumberService.getSpamPhoneNumbers();
    }

    @GetMapping("/nospam")
    public List<PhoneNumberDTO> getNoSpamPhoneNumbers() {
        return phoneNumberService.getNoSpamPhoneNumbers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhoneNumberDTO> getPhoneNumberById(@PathVariable Long id) {
        PhoneNumberDTO dto = phoneNumberService.getPhoneNumberById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @PostMapping("/create")
    public ResponseEntity<PhoneNumberDTO> createPhoneNumber(@RequestBody PhoneNumberDTO phoneNumberDTO) {
        PhoneNumberDTO created = phoneNumberService.createPhoneNumber(phoneNumberDTO);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PhoneNumberDTO> updatePhoneNumber(@PathVariable Long id, @RequestBody PhoneNumberDTO dto) {
        PhoneNumberDTO updated = phoneNumberService.updatePhoneNumber(id, dto);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePhoneNumber(@PathVariable Long id) {
        phoneNumberService.deletePhoneNumber(id);
        return ResponseEntity.noContent().build();
    }
}