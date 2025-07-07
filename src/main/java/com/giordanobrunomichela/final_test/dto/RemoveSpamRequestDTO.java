package com.giordanobrunomichela.final_test.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RemoveSpamRequestDTO {
    @Schema(example = "+666-666-666", description = "Phone number to remove spam reports for")
    private String phoneNumber;
}