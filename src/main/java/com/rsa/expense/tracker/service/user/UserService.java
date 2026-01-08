package com.rsa.expense.tracker.service.user;

import com.rsa.expense.tracker.dto.UserRegistrationRequest;
import com.rsa.expense.tracker.model.User;

public interface UserService {

    User create(UserRegistrationRequest request);

    User find(String username);
}
