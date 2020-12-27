package com.pineapple.tasktracker.controller;

import com.pineapple.tasktracker.model.Project;
import com.pineapple.tasktracker.repository.ProjectRepository;
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

	@RequestMapping(value="/myprojects", method=RequestMethod.GET)
	public String projects(Model model)
	{
		List<Project> projects = (List<Project>) projectRepository.findAll();
		model.addAttribute("projects", projects);
		return "myprojects";//here your name of your view (html)
	}
}
