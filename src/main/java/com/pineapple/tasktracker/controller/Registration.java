package com.pineapple.tasktracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Registration {

    @GetMapping("/registration")
    public String greeting(Model model) {

        return "registration";
    }
}
