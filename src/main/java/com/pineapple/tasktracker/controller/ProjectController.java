package com.pineapple.tasktracker.controller;

import com.pineapple.tasktracker.model.Project;
import com.pineapple.tasktracker.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProjectController {

	@Autowired
	private ProjectRepository projectRepository;
	
	@GetMapping("/welcome")
	public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		name = "AizaT";
		model.addAttribute("name", name);
		return "index";
	}
	
	@RequestMapping(value="/projects", method=RequestMethod.GET)
	public String projects(Model model)
	{
	   List<Project> projects = (List<Project>) projectRepository.findAll();
	   model.addAttribute("projects", projects);
	   return "projects";//here your name of your view (html)
	}
	
}
