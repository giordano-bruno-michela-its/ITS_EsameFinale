package com.giordanobrunomichela.final_test.repository;

import com.giordanobrunomichela.final_test.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> { }