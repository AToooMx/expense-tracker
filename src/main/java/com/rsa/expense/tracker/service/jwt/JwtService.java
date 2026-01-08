package com.rsa.expense.tracker.service.jwt;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

    String generateToken(UserDetails user);

    String extractUsername(String token);

    boolean isValidToken(String token, UserDetails userDetails);

}
