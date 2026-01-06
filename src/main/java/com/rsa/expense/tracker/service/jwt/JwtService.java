package com.rsa.expense.tracker.service.jwt;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

    String generateToken(UserDetails user);

    Claims extractClaims(String token);

    String extractUsername(String token);

    boolean isValidToken(String token, UserDetails user);

}
