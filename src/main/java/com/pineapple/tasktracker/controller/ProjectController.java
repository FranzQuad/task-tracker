package com.pineapple.tasktracker.controller;

import com.pineapple.tasktracker.dto.IssueDto;
import com.pineapple.tasktracker.dto.ProjectDto;
import com.pineapple.tasktracker.dto.RegistrationDto;
import com.pineapple.tasktracker.model.*;
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
import java.util.ArrayList;
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

        for (Issue issue: project.getIssues()) {
            if ((issue.getDeadline() != null) && (issue.getIssueStatus() != IssueStatus.COMPLETE)) {
                Date date = new Date();
                Timestamp currentDate = new Timestamp(date.getTime());
                if ((issue.getIssueStatus() == IssueStatus.OUTDATED) && (currentDate.compareTo(issue.getDeadline()) < 0)) {
                    issue.setIssueStatus(IssueStatus.TO_DO);
                }
                if ((issue.getIssueStatus() != IssueStatus.COMPLETE) && (currentDate.compareTo(issue.getDeadline()) > 0)) {
                    issue.setIssueStatus(IssueStatus.OUTDATED);
                }
                issueRepository.save(issue);
            }
        }

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        User user = userRepository.findByName(username).orElseThrow();

        List<Issue> issues = issueRepository.findByIssueProject(project);

        boolean allTasksCompleted = true;
        for (Issue i : issues) {
            if (i.getIssueStatus() != IssueStatus.COMPLETE) {
                allTasksCompleted = false;
                break;
            }
        }

        List<Project> projects = projectRepository.findByUser(user);
        List<User> users = userRepository.findAll();
        List<ProjectParticipant> projectParticipants = projectParticipantRepository.findAllByProject(project);

        // Issues by status
        List<Issue> toDoIssues = issueRepository.findAllByIssueProjectAndIssueStatus(project, IssueStatus.TO_DO);
        List<Issue> inProgressIssues = issueRepository.findAllByIssueProjectAndIssueStatus(project, IssueStatus.IN_PROGRESS);
        List<Issue> readyForTestingIssues = issueRepository.findAllByIssueProjectAndIssueStatus(project, IssueStatus.READY_FOR_TESTING);
        List<Issue> completeIssues = issueRepository.findAllByIssueProjectAndIssueStatus(project, IssueStatus.COMPLETE);
        List<Issue> outdatedIssues = issueRepository.findAllByIssueProjectAndIssueStatus(project, IssueStatus.OUTDATED);

        model.addAttribute("project", project);
        model.addAttribute("projects", projects);
        model.addAttribute("issues", issues);
        model.addAttribute("users", users);
        model.addAttribute("statusList", new IssueStatus[]{IssueStatus.COMPLETE, IssueStatus.IN_PROGRESS, IssueStatus.READY_FOR_TESTING, IssueStatus.TO_DO});
        model.addAttribute("username", username);
        model.addAttribute("projectparticipants", projectParticipants);
        model.addAttribute("roles", new ProjectRole[]{ProjectRole.MANAGER, ProjectRole.ARCHITECT, ProjectRole.ANALYST, ProjectRole.DEVLEAD, ProjectRole.QA, ProjectRole.DEVELOPER, ProjectRole.TESTER});

        model.addAttribute("todoissues", toDoIssues);
        model.addAttribute("inprogissues", inProgressIssues);
        model.addAttribute("readyfortestingissues", readyForTestingIssues);
        model.addAttribute("completeIssues", completeIssues);
        model.addAttribute("outdatedIssues", outdatedIssues);
        model.addAttribute("allTasksCompleted", allTasksCompleted);

        return "site/project";
    }

    @PostMapping(value = "/project/add-issue")
    public String addIssueForProject(@ModelAttribute IssueDto issueDto) {
        Project project = projectRepository.findById(issueDto.getProjectId()).orElseThrow();
        Issue issue = new Issue();

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        User reporter = userRepository.findByName(username).orElseThrow();
        List<Long> projectParticipantsIds = projectParticipantRepository.findAllByProject(project).stream()
                .map(projectParticipant -> projectParticipant.getUser().getId())
                .collect(Collectors.toList());

        issue.setReporter(reporter);
        issue.setIssueProject(project);
        issue.setName(issueDto.getName());
        issue.setDescription(issueDto.getDescription());
        issue.setCreated(new Timestamp(new Date().getTime()));

        if (issue.getStarted() != null) {
            issue.setStarted(new Timestamp(issueDto.getStarted().getTime()));
        } else {
            issue.setStarted(null);
        }
        if (issue.getDeadline() != null) {
            issue.setDeadline(new Timestamp(issueDto.getDeadline().getTime()));
        } else {
            issue.setDeadline(null);
        }

        issue.setProjectParticipants(issueDto.getUserIds().stream()
                .map(userId -> {
                    if (projectParticipantsIds.contains(userId)) {
                        return projectParticipantRepository.getByUserId(userId);
                    } else {
                        return projectParticipantRepository.save(
                                ProjectParticipant.builder()
                                        .user(userRepository.getOne(userId))
                                        .project(project)
                                        .projectRole(ProjectRole.DEVELOPER)
                                        .build());
                    }
                })
                .collect(Collectors.toList())
        );
        issue.setIssueStatus(IssueStatus.TO_DO);
        issueRepository.save(issue);
        return "redirect:/myprojects";
    }

    @PostMapping(value = "/participant/{participantId}/edit-role")
    public String editParticipant(@PathVariable Long participantId, @RequestParam String role) {
        ProjectParticipant projectParticipant = projectParticipantRepository.getOne(participantId);
        projectParticipant.setProjectRole(ProjectRole.valueOf(role));
        projectParticipantRepository.save(projectParticipant);
        return "redirect:/myprojects";
    }

    @PostMapping(value = "/project/{projectId}/edit-description")
    public String editDescription(@PathVariable Long projectId, @RequestParam String description) {
        Project project = projectRepository.findById(projectId).orElseThrow();
        project.setDescription(description);
        projectRepository.save(project);

        return "redirect:/project/{projectId}";
    }

    @PostMapping(value = "/project/{projectId}/edit-name")
    public String editName(@PathVariable Long projectId, @RequestParam String name) {
        Project project = projectRepository.findById(projectId).orElseThrow();
        project.setName(name);
        projectRepository.save(project);

        return "redirect:/project/{projectId}";
    }

    @PostMapping(value = "/project/{projectId}/edit-startdate")
    public String editStartDate(@PathVariable Long projectId, @ModelAttribute ProjectDto projectDto) {
        Project project = projectRepository.findById(projectId).orElseThrow();
        project.setStarted(new Timestamp(projectDto.getStarted().getTime()));
        projectRepository.save(project);

        return "redirect:/project/{projectId}";
    }

    @PostMapping(value = "/project/{projectId}/edit-deadline")
    public String editDeadline(@PathVariable Long projectId, @ModelAttribute ProjectDto projectDto) {
        Project project = projectRepository.findById(projectId).orElseThrow();
        project.setDeadline(new Timestamp(projectDto.getDeadline().getTime()));
        projectRepository.save(project);

        return "redirect:/project/{projectId}";
    }

    @PostMapping(value = "/project/{projectId}/edit-participant/{partId}")
    public String editParticipant(@PathVariable Long projectId, @PathVariable Long partId, @RequestParam(value = "role") String role) {

        ProjectParticipant projectParticipant = projectParticipantRepository.findById(partId).orElseThrow();

        projectParticipant.setProjectRole(ProjectRole.valueOf(role));

        projectParticipantRepository.save(projectParticipant);

        return "redirect:/project/{projectId}";
    }

    @PostMapping(value = "/project/{projectId}/delete-participant/{partId}")
    public String deleteParticipant(@PathVariable Long projectId, @PathVariable Long partId) {

        ProjectParticipant projectParticipant = projectParticipantRepository.findById(partId).orElseThrow();

        projectParticipantRepository.delete(projectParticipant);

        return "redirect:/project/{projectId}";
    }

    @PostMapping(value = "/project/{projectId}/add-participant")
    public String addParticipant(@PathVariable Long projectId, @ModelAttribute RegistrationDto registrationDto, @RequestParam(value = "role") String role) {
        Project project = projectRepository.findById(projectId).orElseThrow();
        User user = userRepository.findByEmail(registrationDto.getEmail()).orElseThrow();
        ProjectParticipant projectParticipant = new ProjectParticipant();

        projectParticipant.setProject(project);
        projectParticipant.setUser(user);
        projectParticipant.setProjectRole(ProjectRole.valueOf(role));

        projectParticipantRepository.save(projectParticipant);

        return "redirect:/project/{projectId}";
    }

    @PostMapping(value = "/project/{projectId}/complete")
    public String complete(@PathVariable Long projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow();
        project.setFinished(new Timestamp(new Date().getTime()));
        projectRepository.save(project);

        return "redirect:/project/{projectId}";
    }

    @PostMapping(value = "/project/{projectId}/delete")
    public String deleteProject(@PathVariable Long projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow();

        projectRepository.delete(project);

        return "redirect:/myprojects";
    }
}