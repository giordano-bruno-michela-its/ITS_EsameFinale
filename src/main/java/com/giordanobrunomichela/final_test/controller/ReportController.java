package com.giordanobrunomichela.final_test.controller;

import com.giordanobrunomichela.final_test.dto.RemoveSpamRequestDTO;
import com.giordanobrunomichela.final_test.dto.ReportDTO;
import com.giordanobrunomichela.final_test.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reports")
@CrossOrigin(origins = "http://localhost:3000")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/all")
    public List<ReportDTO> getAllReports() {
        return reportService.getAllReports();
    }

    @GetMapping("/spam")
    public List<ReportDTO> getSpamReports() {
        return reportService.getSpamReports();
    }

    @GetMapping("/nospam")
    public List<ReportDTO> getNoSpamReports() {
        return reportService.getNoSpamReports();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReportDTO> getReportById(@PathVariable Long id) {
        ReportDTO dto = reportService.getReportById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @PostMapping("/create")
    public ResponseEntity<ReportDTO> createReport(@RequestBody ReportDTO reportDTO) {
        ReportDTO created = reportService.createReport(reportDTO);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ReportDTO> updateReport(@PathVariable Long id, @RequestBody ReportDTO dto) {
        ReportDTO updated = reportService.updateReport(id, dto);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable Long id) {
        reportService.deleteReport(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/remove-spam")
    public ResponseEntity<String> removeAllSpamReports(@RequestBody RemoveSpamRequestDTO request) {
        String phoneNumber = request.getPhoneNumber();

        if (phoneNumber == null || phoneNumber.isEmpty()) {
            return ResponseEntity.badRequest().body("Phone number is required");
        }

        reportService.removeAllSpamReportsForPhoneNumber(phoneNumber);
        return ResponseEntity.ok("All SPAM reports removed for phone number: " + phoneNumber);
    }
}