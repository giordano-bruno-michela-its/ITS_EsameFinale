package com.giordanobrunomichela.final_test.dto;

import com.giordanobrunomichela.final_test.model.PhoneNumberStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PhoneNumberDTO {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(example = "Commercial telemarketing", description = "Description of the phone number")
    private String description;

    @Schema(example = "+1234567890", description = "Phone number")
    private String phoneNumber;

    @Schema(example = "0", description = "Number of spam reports for this phone number")
    private Integer spamReportCount;

    @Schema(example = "0", description = "Number of no-spam reports for this phone number")
    private Integer noSpamReportCount;

    @Schema(example = "NOSPAM", description = "Status of the phone number (NOSPAM or SPAM)")
    private PhoneNumberStatus phoneNumberStatus;
}