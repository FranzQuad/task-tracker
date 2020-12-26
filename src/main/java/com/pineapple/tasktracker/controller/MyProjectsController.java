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
public class MyProjectsController {

	@GetMapping("/myprojects")
	public String addProject(Model model) {

		return "myprojects";
	}
	
}
