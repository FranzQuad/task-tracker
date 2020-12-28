package com.pineapple.tasktracker.controller;

import com.pineapple.tasktracker.dto.IssueDto;
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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class ProjectController {
    private final IssueRepository issueRepository;
    private final ProjectRepository projectRepository;
    private final ProjectParticipantRepository projectParticipantRepository;
    private final UserRepository userRepository;

    @GetMapping(value = "/project/{id}")
    public String getProjectById(Model model, @PathVariable long id) {
        Project project = projectRepository.findById(id).orElseThrow();

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        User user = userRepository.findByName(username).orElseThrow();

        List<Issue> issues = issueRepository.findByUser(user);
        List<Project> projects = projectRepository.findByUser(user);
        List<User> users = userRepository.findAll();

        model.addAttribute("project", project);
        model.addAttribute("projects", projects);
        model.addAttribute("issues", issues);
        model.addAttribute("users", users);
        model.addAttribute("statusList", new IssueStatus[] {IssueStatus.COMPLETE, IssueStatus.IN_PROGRESS, IssueStatus.READY_FOR_TESTING, IssueStatus.TO_DO});
        return "project";
    }

    @PostMapping(value = "/project/add-issue")
    public String addIssueForProject(@ModelAttribute IssueDto issueDto) {
        Project project = projectRepository.findById(issueDto.getProjectId()).orElseThrow();
        Issue issue = new Issue();
        issue.setIssueProject(project);
        issue.setName(issueDto.getName());
        issue.setDescription(issueDto.getDescription());
        issue.setStarted(new Timestamp(new Date().getTime()));
        issue.setFinished(new Timestamp(issueDto.getDate().getTime()));
        issue.setProjectParticipants(issueDto.getUserIds().stream()
                .map(userId -> projectParticipantRepository.save(
                        ProjectParticipant.builder()
                                .user(userRepository.getOne(userId))
                                .project(project)
                                .projectRole(ProjectRole.DEVELOPER)
                                .build()
                ))
                .collect(Collectors.toList())
        );
        issue.setIssueStatus(IssueStatus.valueOf(issueDto.getIssueStatus()));
        issueRepository.save(issue);
        return "project";
    }

    @PostMapping(value = "/issue/{issueId}/edit-status")
    public String changeIssueStatus(@PathVariable Long issueId, @RequestParam String status) {
        Issue issue = issueRepository.findById(issueId).orElseThrow();
        issue.setIssueStatus(IssueStatus.valueOf(status));
        issueRepository.save(issue);
        return "project";
    }

    @PostMapping(value = "/participant/{participantId}/edit-role")
    public String editParticipant(@PathVariable Long participantId, @RequestParam String role) {
        ProjectParticipant projectParticipant = projectParticipantRepository.getOne(participantId);
        projectParticipant.setProjectRole(ProjectRole.valueOf(role));
        projectParticipantRepository.save(projectParticipant);
        return "project";
    }
}