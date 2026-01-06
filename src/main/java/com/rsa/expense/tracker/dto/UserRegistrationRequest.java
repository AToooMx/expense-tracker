package com.rsa.expense.tracker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationRequest {
    @NotBlank(message = "username can't be blank")
    private String username;
    @NotBlank(message = "password can't be blank")
    private String password;
    @NotBlank(message = "firstName can't be blank")
    private String firstName;
    @NotBlank(message = "middleName can't be blank")
    private String middleName;
    @NotBlank(message = "lastName can't be blank")
    private String lastName;
    @NotNull(message = "birthday can't be null")
    private LocalDate birthday;
}
