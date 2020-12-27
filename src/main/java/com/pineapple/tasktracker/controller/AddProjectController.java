package com.pineapple.tasktracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AddProjectController {

    @GetMapping("/addproject")
    public String addProject(Model model) {

        return "addproject";
    }
}
