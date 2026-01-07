package com.rsa.expense.tracker.service.refreshtoken;

import com.rsa.expense.tracker.model.RefreshToken;
import com.rsa.expense.tracker.model.User;

import java.util.Optional;

public interface RefreshTokenService {

    String createRefreshToken(User user);

    Optional<RefreshToken> findByToken(String refreshToken);

    boolean isExpiredToken(RefreshToken refreshToken);

    void deleteToken(RefreshToken refreshToken);

    String updateRefreshToken(RefreshToken refreshToken);
}
