package com.giordanobrunomichela.final_test.repository;

import com.giordanobrunomichela.final_test.model.Report;
import com.giordanobrunomichela.final_test.model.ReportType;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findByReportType(ReportType reportType);
 }