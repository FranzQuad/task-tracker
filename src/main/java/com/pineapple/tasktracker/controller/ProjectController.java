package com.pineapple.tasktracker.controller;

import com.pineapple.tasktracker.dto.IssueDto;
import com.pineapple.tasktracker.model.Issue;
import com.pineapple.tasktracker.model.Project;
import com.pineapple.tasktracker.model.ProjectParticipant;
import com.pineapple.tasktracker.model.enums.IssueStatus;
import com.pineapple.tasktracker.model.enums.ProjectRole;
import com.pineapple.tasktracker.repository.IssueRepository;
import com.pineapple.tasktracker.repository.ProjectParticipantRepository;
import com.pineapple.tasktracker.repository.ProjectRepository;
import com.pineapple.tasktracker.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Date;
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
        model.addAttribute("issues", project.getIssues());
        model.addAttribute("participants", project.getProjectParticipants());
        return "project";
    }

    @PostMapping(value = "/project/{id}/add-issue")
    public String addIssueForProject(@ModelAttribute IssueDto issueDto, @PathVariable long id) {
        Project project = projectRepository.findById(id).orElseThrow();
        Issue issue = new Issue();
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
        issue.setIssueStatus(issueDto.getIssueStatus());
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