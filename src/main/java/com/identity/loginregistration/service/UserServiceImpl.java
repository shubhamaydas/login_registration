package com.identity.loginregistration.service;

import com.identity.loginregistration.model.Role;
import com.identity.loginregistration.model.User;
import com.identity.loginregistration.repository.UserRepository;
import com.identity.loginregistration.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(UserRegistrationDto registrationDto) {
        User user = User.builder().firstName(registrationDto.getFirstName())
                .lastName(registrationDto.getLastName())
                .email(registrationDto.getEmail())
                .password(registrationDto.getPassword())
                .roles(Arrays.asList(Role.builder().name("ROLE_USER").build()))
                .build();
        return userRepository.save(user);
    }
}
