package com.pineapple.tasktracker.controller;

import com.pineapple.tasktracker.model.Issue;
import com.pineapple.tasktracker.model.Project;
import com.pineapple.tasktracker.repository.IssueRepository;
import com.pineapple.tasktracker.repository.ProjectRepository;
import com.pineapple.tasktracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class MyProjectsController {

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private IssueRepository issueRepository;
	
	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value="/myprojects", method=RequestMethod.GET)
	public String projects(Model model) {
		List<Issue> issues = issueRepository.findAll();
		List<Project> projects = projectRepository.findAll();

		model.addAttribute("projects", projects);
		model.addAttribute("issues", issues);

		return "myprojects";
	}
}
