package com.giordanobrunomichela.final_test.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Date reportDate;
    private String description;
    
    @Enumerated(EnumType.STRING)
    private ReportType reportType = ReportType.NOSPAM;
    
    private String phoneNumber;
    
    private Long phoneNumberId;
}
