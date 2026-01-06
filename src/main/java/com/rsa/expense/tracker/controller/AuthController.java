package com.rsa.expense.tracker.controller;

import com.rsa.expense.tracker.dto.UserDto;
import com.rsa.expense.tracker.dto.UserLoginRequest;
import com.rsa.expense.tracker.dto.UserLoginResponse;
import com.rsa.expense.tracker.dto.UserRegistrationRequest;
import com.rsa.expense.tracker.service.auth.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public UserDto registration(@Valid @RequestBody UserRegistrationRequest request) {
        return authService.registration(request);
    }

    @PostMapping("/login")
    public UserLoginResponse login(@Valid @RequestBody UserLoginRequest request) {
        return authService.login(request);
    }

}
