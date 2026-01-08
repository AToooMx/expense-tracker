package com.rsa.expense.tracker.service.refreshtoken;

import com.rsa.expense.tracker.model.RefreshToken;
import com.rsa.expense.tracker.model.User;
import com.rsa.expense.tracker.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UUIDRefreshTokenService implements RefreshTokenService {
    @Value("${security.jwt.refresh-expiration-time}")
    private Long refreshTokenExpiration;
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    @Transactional
    public String createRefreshToken(User user) {
        var refreshToken = refreshTokenRepository.findByUserId(user.getId()).orElseGet(RefreshToken::new);
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setUser(user);
        refreshToken.setExpiredAt(LocalDateTime.now().plus(refreshTokenExpiration, ChronoUnit.MILLIS));
        refreshTokenRepository.save(refreshToken);
        return refreshToken.getToken();
    }

    @Override
    public Optional<RefreshToken> findByToken(String refreshToken) {
        return refreshTokenRepository.findByToken(refreshToken);
    }

    @Override
    public boolean isExpiredToken(RefreshToken refreshToken) {
        return refreshToken.getExpiredAt().isBefore(LocalDateTime.now());
    }

    @Override
    @Transactional
    public void deleteToken(RefreshToken refreshToken) {
        refreshTokenRepository.delete(refreshToken);
    }

    @Override
    public String updateRefreshToken(RefreshToken refreshToken) {
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshTokenRepository.save(refreshToken);
        return refreshToken.getToken();
    }

}
