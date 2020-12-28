package com.pineapple.tasktracker.controller;

import com.pineapple.tasktracker.dto.ProjectDto;
import com.pineapple.tasktracker.model.Issue;
import com.pineapple.tasktracker.model.Project;
import com.pineapple.tasktracker.model.ProjectParticipant;
import com.pineapple.tasktracker.model.User;
import com.pineapple.tasktracker.model.enums.ProjectRole;
import com.pineapple.tasktracker.repository.IssueRepository;
import com.pineapple.tasktracker.repository.ProjectParticipantRepository;
import com.pineapple.tasktracker.repository.ProjectRepository;
import com.pineapple.tasktracker.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class MyProjectsController {

	private final ProjectRepository projectRepository;
	private final UserRepository userRepository;
	private final ProjectParticipantRepository projectParticipantRepository;
	private final IssueRepository issueRepository;

	@RequestMapping(value="/myprojects", method=RequestMethod.GET)
	public String projects(Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		User user = userRepository.findByName(username).orElseThrow();

		List<Issue> issues = issueRepository.findByUser(user);
		List<Project> projects = projectRepository.findByUser(user);

		model.addAttribute("projects", projects);
		model.addAttribute("issues", issues);

		return "myprojects";
	}

	@PostMapping(value="/add-project")
	String addNewProject(@ModelAttribute ProjectDto projectDto) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		User user = userRepository.findByName(username).orElseThrow();

		Project project = new Project();
		project.setName(projectDto.getName());
		project.setDescription("New awesome project");
		project.setFinished(new Timestamp(12323));
		project.setStarted(new Timestamp(123123));
		project.setProjectParticipants(new ArrayList<>());
		project = projectRepository.save(project);

		ProjectParticipant projectParticipant = new ProjectParticipant();
		projectParticipant.setProject(project);
		projectParticipant.setUser(user);
		projectParticipant.setProjectRole(ProjectRole.MANAGER);
		projectParticipant = projectParticipantRepository.save(projectParticipant);

		project.getProjectParticipants().add(projectParticipant);
		projectRepository.save(project);

		return "redirect:/myprojects";
	}
}
