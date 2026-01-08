package com.rsa.expense.tracker.service.auth;

import com.rsa.expense.tracker.dto.*;
import com.rsa.expense.tracker.exception.CustomException;
import com.rsa.expense.tracker.exception.Error;
import com.rsa.expense.tracker.mapper.UserMapper;
import com.rsa.expense.tracker.service.jwt.JwtService;
import com.rsa.expense.tracker.service.refreshtoken.RefreshTokenService;
import com.rsa.expense.tracker.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationProvider authenticationProvider;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;
    private final JwtService jwtService;
    private final UserMapper userMapper;

    @Override
    public UserDto registration(UserRegistrationRequest request) {
        var user = userService.create(request);
        return userMapper.toDto(user);
    }

    @Override
    public UserLoginResponse login(UserLoginRequest request) {

        authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));

        var user = userService.find(request.getUsername());

        return UserLoginResponse.builder()
                .accessToken(jwtService.generateToken(user))
                .refreshToken(refreshTokenService.createRefreshToken(user))
                .build();
    }

    @Override
    public RefreshTokenResponse refreshToken(RefreshTokenRequest request) {
        return refreshTokenService.findByToken(request.getRefreshToken())
                .map(refreshToken -> {
                    if (refreshTokenService.isExpiredToken(refreshToken)) {
                        refreshTokenService.deleteToken(refreshToken);
                        throw new CustomException(Error.EXPIRATION_RESFRESH_TOKEN_ERROR, "RefreshToken is expired");
                    }

                    return RefreshTokenResponse.builder()
                            .accessToken(jwtService.generateToken(refreshToken.getUser()))
                            .refreshToken(refreshTokenService.updateRefreshToken(refreshToken))
                            .build();
                })
                .orElseThrow(() -> new CustomException(Error.ENTITY_NOT_FOUND, "Not found refresh token"));
    }

    @Override
    public void logout(LogoutRequest request) {
        var refreshToken = refreshTokenService.findByToken(request.getRefreshToken())
                .orElseThrow(() -> new CustomException(Error.ENTITY_NOT_FOUND, "Not found refresh token"));
        refreshTokenService.deleteToken(refreshToken);
    }

}
