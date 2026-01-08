package com.rsa.expense.tracker.controller;

import com.rsa.expense.tracker.dto.*;
import com.rsa.expense.tracker.service.auth.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Tag(name = "Auth")
public class AuthController {
    private final AuthService authService;

    @Operation(summary = "Registration new user")
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto registration(@Valid @RequestBody UserRegistrationRequest request) {
        return authService.registration(request);
    }

    @Operation(summary = "User login")
    @PostMapping("/login")
    public UserLoginResponse login(@Valid @RequestBody UserLoginRequest request) {
        return authService.login(request);
    }

    @Operation(summary = "Refresh access token")
    @PostMapping("/refresh")
    public RefreshTokenResponse refresh(@Valid @RequestBody RefreshTokenRequest request) {
        return authService.refreshToken(request);
    }

    @Operation(summary = "Logout user")
    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(@Valid @RequestBody LogoutRequest request) {
        authService.logout(request);
    }

}
