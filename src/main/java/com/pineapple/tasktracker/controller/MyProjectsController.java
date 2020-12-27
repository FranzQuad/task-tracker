package com.pineapple.tasktracker.controller;

import com.pineapple.tasktracker.model.Issue;
import com.pineapple.tasktracker.model.Project;
import com.pineapple.tasktracker.model.Projectparticipant;
import com.pineapple.tasktracker.model.User;
import com.pineapple.tasktracker.repository.IssueRepository;
import com.pineapple.tasktracker.repository.IssuelabelRepository;
import com.pineapple.tasktracker.repository.ProjectRepository;
import com.pineapple.tasktracker.repository.ProjectparticipantRepository;
import com.pineapple.tasktracker.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MyProjectsController {

	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private ProjectparticipantRepository projectParticipantRepository;

	@Autowired
	private IssueRepository issueRepository;
	
	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value="/myprojects", method=RequestMethod.GET)
	public String projects(Model model)
	{
		List<Issue> issues = (List<Issue>) issueRepository.findAll();

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		
		User user = userRepository.findByName(username);
		List<Projectparticipant> projectParticipants = (List<Projectparticipant>)projectParticipantRepository.findAllByUserBean_Id(user.getId());
		List<Long> projectIds = new ArrayList<>();
		for (Projectparticipant participant: projectParticipants) {
			projectIds.add(participant.getProjectBean().getId());
		}
		List<Project> projects = (List<Project>) projectRepository.findAllById(projectIds);

		model.addAttribute("projects", projects);
		model.addAttribute("issues", issues);

		return "myprojects";//here your name of your view (html)
	}
}
