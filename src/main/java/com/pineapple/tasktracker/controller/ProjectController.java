package com.pineapple.tasktracker.controller;

import com.pineapple.tasktracker.dto.IssueDto;
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

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        User user = userRepository.findByName(username).orElseThrow();

        List<Issue> issues = issueRepository.findByUser(user);
        List<Project> projects = projectRepository.findByUser(user);
        List<User> users = userRepository.findAll();
        List<ProjectParticipant> projectParticipants = projectParticipantRepository.findAllByProject(project);

        // Issues by status
        List<Issue> toDoIssues = issueRepository.findAllByIssueProjectAndIssueStatus(project, IssueStatus.TO_DO);
        List<Issue> inProgressIssues = issueRepository.findAllByIssueProjectAndIssueStatus(project, IssueStatus.IN_PROGRESS);
        List<Issue> readyForTestingIssues = issueRepository.findAllByIssueProjectAndIssueStatus(project, IssueStatus.READY_FOR_TESTING);
        List<Issue> completeIssues = issueRepository.findAllByIssueProjectAndIssueStatus(project, IssueStatus.COMPLETE);

        model.addAttribute("project", project);
        model.addAttribute("projects", projects);
        model.addAttribute("issues", issues);
        model.addAttribute("users", users);
        model.addAttribute("statusList", new IssueStatus[]{IssueStatus.COMPLETE, IssueStatus.IN_PROGRESS, IssueStatus.READY_FOR_TESTING, IssueStatus.TO_DO});
        model.addAttribute("username", username);
        model.addAttribute("projectparticipants", projectParticipants);

        model.addAttribute("todoissues", toDoIssues);
        model.addAttribute("inprogissues", inProgressIssues);
        model.addAttribute("readyfortestingissues", readyForTestingIssues);
        model.addAttribute("completeIssues", completeIssues);
        return "site/project";
    }

//    @PostMapping(value = "/add-project")
//    String addNewProject(@ModelAttribute ProjectDto projectDto) {
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        String username = ((UserDetails) principal).getUsername();
//        User user = userRepository.findByName(username).orElseThrow();
//
//        Project project = new Project();
//        project.setName(projectDto.getName());
//        project.setDescription(projectDto.getDescription());
//        project.setFinished(new Timestamp(projectDto.getDate().getTime()));
//        project.setStarted(new Timestamp(new Date().getTime()));
//        project.setProjectParticipants(new ArrayList<>());
//        project = projectRepository.save(project);
//
//        ProjectParticipant projectParticipant = projectParticipantRepository.save(
//                ProjectParticipant.builder()
//                        .project(project)
//                        .user(user)
//                        .projectRole(ProjectRole.MANAGER)
//                        .build()
//        );
//
//        project.getProjectParticipants().add(projectParticipant);
//        projectRepository.save(project);
//
//        return "redirect:/myprojects";
//    }

    @PostMapping(value = "/project/add-issue")
    public String addIssueForProject(@ModelAttribute IssueDto issueDto) {
        Project project = projectRepository.findById(issueDto.getProjectId()).orElseThrow();
        Issue issue = new Issue();

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        User reporter = userRepository.findByName(username).orElseThrow();

        issue.setReporter(reporter);
        issue.setIssueProject(project);
        issue.setName(issueDto.getName());
        issue.setDescription(issueDto.getDescription());
        issue.setCreated(new Timestamp(new Date().getTime()));
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

    /*
    TODO: Не доделано, проект не удаляется из-за issue participants
     */
    @PostMapping(value = "/project/{projectId}/delete")
    public String deleteProject(@PathVariable Long projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow();
        List<Issue> projectIssues = issueRepository.findByProject(project);
        List<ProjectParticipant> projectParticipants = projectParticipantRepository.findAllByProject(project);

        projectParticipantRepository.deleteAll(projectParticipants);
        issueRepository.deleteAll(projectIssues);
        projectRepository.delete(project);

        return "redirect:/myprojects";
    }
}