package com.pineapple.tasktracker.controller;

import com.pineapple.tasktracker.dto.RegistrationDto;
import com.pineapple.tasktracker.model.User;
import com.pineapple.tasktracker.model.enums.Role;
import com.pineapple.tasktracker.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class Registration {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/registration")
    public String registrationPage() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUser(@ModelAttribute RegistrationDto registrationDto) {
        if (registrationDto.getName() == null || registrationDto.getName().isEmpty()) {
            return "redirect:/registration";
        }
        User user = new User();
        user.setRole(Role.ROLE_USER);
        user.setName(registrationDto.getName());
        user.setLogin(registrationDto.getName());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        userRepository.save(user);
        return "redirect:/login";
    }
}
