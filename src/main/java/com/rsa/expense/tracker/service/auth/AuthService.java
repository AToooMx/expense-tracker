package com.rsa.expense.tracker.service.auth;

import com.rsa.expense.tracker.dto.*;

public interface AuthService {

    UserDto registration(UserRegistrationRequest request);

    UserLoginResponse login(UserLoginRequest request);

    RefreshTokenResponse refreshToken(RefreshTokenRequest request);

    void logout(LogoutRequest request);
}
