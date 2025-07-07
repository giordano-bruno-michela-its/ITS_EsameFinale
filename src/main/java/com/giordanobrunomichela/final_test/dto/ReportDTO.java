package com.giordanobrunomichela.final_test.dto;

import com.giordanobrunomichela.final_test.model.ReportType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
public class ReportDTO {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Date when the report was created")
    private Date reportDate;

    @Schema(example = "General commercial call", description = "Description of the report")
    private String description;

    @Schema(example = "NOSPAM", description = "Type of report (NOSPAM or SPAM)")
    private ReportType reportType;
}