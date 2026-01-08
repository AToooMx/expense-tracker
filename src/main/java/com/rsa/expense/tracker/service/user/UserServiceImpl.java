package com.rsa.expense.tracker.service.user;

import com.rsa.expense.tracker.dto.UserRegistrationRequest;
import com.rsa.expense.tracker.exception.CustomException;
import com.rsa.expense.tracker.exception.Error;
import com.rsa.expense.tracker.model.Role;
import com.rsa.expense.tracker.model.User;
import com.rsa.expense.tracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User create(UserRegistrationRequest request) {

        if (isUserExists(request.getUsername())) {
            throw new CustomException(Error.CREATE_USER_ERROR, "User with username=%s is already exists".formatted(request.getUsername()));
        }

        var user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .middleName(request.getMiddleName())
                .lastName(request.getLastName())
                .birthday(request.getBirthday())
                .role(Role.USER)
                .build();

        return userRepository.save(user);
    }

    @Override
    public User find(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomException(Error.ENTITY_NOT_FOUND, "User with username=%s not found".formatted(username)));
    }

    private boolean isUserExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

}
