package com.pineapple.tasktracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pineapple.tasktracker.model.Project;
import com.pineapple.tasktracker.repository.ProjectRepository;

@Controller
public class ProjectController {

	@Autowired
	private ProjectRepository projectRepository;

	
	@RequestMapping(value="/projects", method=RequestMethod.GET)
	public String projects(Model model)
	{
	   List<Project> projects = (List<Project>) projectRepository.findAll();
	   model.addAttribute("projects", projects);
	   return "projects";//here your name of your view (html)
	}
	
}
