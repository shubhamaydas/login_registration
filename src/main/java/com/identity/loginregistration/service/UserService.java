package com.identity.loginregistration.service;

import com.identity.loginregistration.model.User;
import com.identity.loginregistration.web.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto registrationDto);
}
