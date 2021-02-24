package com.identity.loginregistration.controller;

import com.identity.loginregistration.service.UserService;
import com.identity.loginregistration.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    @Autowired
    UserService userService;

    @GetMapping
    public String showRegistrationForm(){
        return "registration";
    }

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto(){
        return UserRegistrationDto.builder().build();
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user")UserRegistrationDto registrationDto){
        userService.save(registrationDto);
        return "redirect:/registration?success";
    }
}
