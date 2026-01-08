package com.rsa.expense.tracker.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogoutRequest {
    @NotBlank(message = "refreshToken can't be blank")
    private String refreshToken;
}
