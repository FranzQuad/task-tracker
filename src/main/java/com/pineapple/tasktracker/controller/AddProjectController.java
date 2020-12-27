package com.pineapple.tasktracker.controller;

import com.pineapple.tasktracker.model.Issue;
import com.pineapple.tasktracker.model.Project;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class AddProjectController {

    @RequestMapping(value="/addproject", method= RequestMethod.GET)
    public String projects(Model model)
    {
        return "addproject";//here your name of your view (html)
    }
}
