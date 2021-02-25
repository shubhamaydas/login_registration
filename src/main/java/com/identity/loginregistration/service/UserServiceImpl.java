package com.identity.loginregistration.service;

import com.identity.loginregistration.model.Role;
import com.identity.loginregistration.model.User;
import com.identity.loginregistration.repository.UserRepository;
import com.identity.loginregistration.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User save(UserRegistrationDto registrationDto) {
        User user = User.builder().firstName(registrationDto.getFirstName())
                .lastName(registrationDto.getLastName())
                .email(registrationDto.getEmail())
                .password(bCryptPasswordEncoder.encode(registrationDto.getPassword()))
                .roles(Arrays.asList(Role.builder().name("ROLE_USER").build()))
                .build();
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if(user==null){
            throw new UsernameNotFoundException("Invalid Username or Password");
        }
    return new org.springframework.security.core.userdetails.User(
            user.getEmail(), user.getPassword(), mapRolesToAuthority(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthority(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
