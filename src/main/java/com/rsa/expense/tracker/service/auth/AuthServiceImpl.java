package com.rsa.expense.tracker.service.auth;

import com.rsa.expense.tracker.dto.UserDto;
import com.rsa.expense.tracker.dto.UserLoginRequest;
import com.rsa.expense.tracker.dto.UserLoginResponse;
import com.rsa.expense.tracker.dto.UserRegistrationRequest;
import com.rsa.expense.tracker.mapper.UserMapper;
import com.rsa.expense.tracker.service.jwt.JwtService;
import com.rsa.expense.tracker.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationProvider authenticationProvider;
    private final UserDetailsService userDetailsService;
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

        var user = userDetailsService.loadUserByUsername(request.getUsername());

        return UserLoginResponse.builder()
                .accessToken(jwtService.generateToken(user))
                .build();
    }

}
