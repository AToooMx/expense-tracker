package com.rsa.expense.tracker.service.auth;

import com.rsa.expense.tracker.dto.UserDto;
import com.rsa.expense.tracker.dto.UserLoginRequest;
import com.rsa.expense.tracker.dto.UserLoginResponse;
import com.rsa.expense.tracker.dto.UserRegistrationRequest;

public interface AuthService {

    UserDto registration(UserRegistrationRequest request);

    UserLoginResponse login(UserLoginRequest request);

}
