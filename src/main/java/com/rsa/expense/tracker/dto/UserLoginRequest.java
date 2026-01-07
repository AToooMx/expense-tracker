package com.rsa.expense.tracker.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLoginRequest {
    @NotBlank(message = "username can't be blank")
    private String username;
    @NotBlank(message = "password can't be blank")
    private String password;
}
