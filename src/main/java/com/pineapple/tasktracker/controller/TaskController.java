package com.pineapple.tasktracker.controller;

import com.pineapple.tasktracker.model.Issue;
import com.pineapple.tasktracker.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class TaskController {

    @Autowired
    private IssueRepository issueRepository;

    @RequestMapping(value="/task", method= RequestMethod.GET)
    public String projects(Model model)
    {
        List<Issue> issues = (List<Issue>) issueRepository.findAll();
        model.addAttribute("issues", issues);

        return "site/task";//here your name of your view (html)
    }
}
