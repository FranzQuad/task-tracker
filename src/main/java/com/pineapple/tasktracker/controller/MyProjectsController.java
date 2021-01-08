package com.pineapple.tasktracker.controller;

import com.pineapple.tasktracker.dto.ProjectDto;
import com.pineapple.tasktracker.model.Issue;
import com.pineapple.tasktracker.model.Project;
import com.pineapple.tasktracker.model.ProjectParticipant;
import com.pineapple.tasktracker.model.User;
import com.pineapple.tasktracker.model.enums.IssueStatus;
import com.pineapple.tasktracker.model.enums.ProjectRole;
import com.pineapple.tasktracker.repository.IssueRepository;
import com.pineapple.tasktracker.repository.ProjectParticipantRepository;
import com.pineapple.tasktracker.repository.ProjectRepository;
import com.pineapple.tasktracker.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Controller
@AllArgsConstructor
@Log
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
		List<User> users = userRepository.findAll();

  
		for (Issue issue: issues) {
			if ((issue.getDeadline() != null ) && (issue.getIssueStatus() != IssueStatus.COMPLETE))
			{
				Date date = new Date();
				Timestamp currentDate = new Timestamp(date.getTime());
				if ((issue.getIssueStatus() == IssueStatus.OUTDATED) && (currentDate.compareTo(issue.getDeadline()) < 0))
				{
					issue.setIssueStatus(IssueStatus.TO_DO);
				}
				if ((issue.getIssueStatus() != IssueStatus.COMPLETE) && (currentDate.compareTo(issue.getDeadline()) > 0))
				{
					issue.setIssueStatus(IssueStatus.OUTDATED);
				}
				issueRepository.save(issue);
			}
		}

		model.addAttribute("projects", projects);
		model.addAttribute("issues", issues);
		model.addAttribute("users", users);
		model.addAttribute("statusList", new IssueStatus[] {IssueStatus.TO_DO, IssueStatus.IN_PROGRESS, IssueStatus.READY_FOR_TESTING, IssueStatus.COMPLETE, IssueStatus.OUTDATED});
		model.addAttribute("username", username);

		return "site/myprojects";
	}

	@PostMapping(value="/add-project")
	String addNewProject(@ModelAttribute ProjectDto projectDto) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		User user = userRepository.findByName(username).orElseThrow();

		Project project = new Project();
		project.setName(projectDto.getName());
		project.setDescription(projectDto.getDescription());
		project.setCreated(new Timestamp(new Date().getTime()));

		if (projectDto.getStarted() != null) {
			project.setStarted(new Timestamp(projectDto.getStarted().getTime()));
		} else {
			project.setStarted(null);
		}

		if (projectDto.getDeadline() != null) {
			project.setDeadline(new Timestamp(projectDto.getDeadline().getTime()));
		} else {
			project.setDeadline(null);
		}

		project.setProjectParticipants(new ArrayList<>());
		project = projectRepository.save(project);

		ProjectParticipant projectParticipant = projectParticipantRepository.save(
				ProjectParticipant.builder()
				.project(project)
				.user(user)
				.projectRole(ProjectRole.MANAGER)
				.build()
		);

		project.getProjectParticipants().add(projectParticipant);
		projectRepository.save(project);

		return "redirect:/myprojects";
	}
}
