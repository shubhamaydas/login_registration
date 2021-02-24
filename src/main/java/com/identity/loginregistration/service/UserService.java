package com.identity.loginregistration.service;

import com.identity.loginregistration.model.User;
import com.identity.loginregistration.web.dto.UserRegistrationDto;

public interface UserService {
    User save(UserRegistrationDto registrationDto);
}
