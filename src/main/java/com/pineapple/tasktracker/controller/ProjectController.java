package com.pineapple.tasktracker.controller;

import com.pineapple.tasktracker.model.Issue;
import com.pineapple.tasktracker.repository.IssueRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@AllArgsConstructor
public class ProjectController {

    private final IssueRepository issueRepository;

    @RequestMapping(value="/project", method= RequestMethod.GET)
    public String projects(Model model)
    {
        List<Issue> issues = issueRepository.findAll();
        model.addAttribute("issues", issues);

        return "project";//here your name of your view (html)
    }
}
