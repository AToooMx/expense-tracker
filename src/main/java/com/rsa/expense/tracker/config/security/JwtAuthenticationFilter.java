package com.rsa.expense.tracker.config.security;

import com.rsa.expense.tracker.service.jwt.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private static final String BEARER_PREFIX = "Bearer ";
    private final AuthenticationProvider authenticationProvider;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (isValidToken(authHeader)) {
            var bearerToken = authHeader.substring(BEARER_PREFIX.length());
            var claims = jwtService.extractClaims(bearerToken);

            if (SecurityContextHolder.getContext().getAuthentication() == null) {
                var user = userDetailsService.loadUserByUsername(claims.getSubject());

                if (jwtService.isValidToken(bearerToken, user)) {
                    var authentication = new UsernamePasswordAuthenticationToken(
                            user.getUsername(), user.getPassword(), user.getAuthorities()
                    );

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }

        filterChain.doFilter(request, response);
    }

    private boolean isValidToken(String authToken) {
        return authToken != null && authToken.startsWith(BEARER_PREFIX);
    }

}
