package com.pineapple.tasktracker.controller;

import com.pineapple.tasktracker.dto.CommentDto;
import com.pineapple.tasktracker.dto.IssueDto;
import com.pineapple.tasktracker.dto.ProjectDto;
import com.pineapple.tasktracker.model.*;
import com.pineapple.tasktracker.model.enums.IssueStatus;
import com.pineapple.tasktracker.model.enums.ProjectRole;
import com.pineapple.tasktracker.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.xml.stream.events.Comment;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class IssueController {

    private final IssueRepository issueRepository;
    private final ProjectRepository projectRepository;
    private final ProjectParticipantRepository projectParticipantRepository;
    private final UserRepository userRepository;
    private final IssueCommentRepository issueCommentRepository;

    @GetMapping(value = "/issue/{id}")
    public String projects(Model model, @PathVariable long id)
    {
        Issue issue = issueRepository.findById(id).orElseThrow();
        Project project = projectRepository.findById(issue.getIssueProject().getId()).orElseThrow();

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        User user = userRepository.findByName(username).orElseThrow();

        List<Issue> issues = issueRepository.findByUser(user);
        List<Project> projects = projectRepository.findByUser(user);
        List<User> users = userRepository.findAll();
        List<IssueComment> comments = issueCommentRepository.findAllByIssue(issue);

        model.addAttribute("project", project);
        model.addAttribute("projects", projects);
        model.addAttribute("issues", issues);
        model.addAttribute("issue", issue);
        model.addAttribute("users", users);
        model.addAttribute("comments", comments);
        model.addAttribute("statusList", new IssueStatus[] {IssueStatus.COMPLETE, IssueStatus.IN_PROGRESS, IssueStatus.READY_FOR_TESTING, IssueStatus.TO_DO});

        return "site/issue";//here your name of your view (html)
    }

    @PostMapping(value = "/issue/{id}/add-comment")
    public String addIssueForProject(@ModelAttribute CommentDto commentDto, @PathVariable long id) {
        System.out.println(commentDto.getText());
        Issue issue = issueRepository.findById(id).orElseThrow();

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        User user = userRepository.findByName(username).orElseThrow();

        Project project = issue.getIssueProject();
        ProjectParticipant projectParticipant = projectParticipantRepository.findFirstByUserAndProject(user, project);

        IssueComment comment = new IssueComment();
        comment.setCreated(new Timestamp(new Date().getTime()));
        comment.setText(commentDto.getText());
        comment.setIssue(issue);
        comment.setAuthor(projectParticipant);
        issueCommentRepository.save(comment);

        return "redirect:/issue/{id}";
    }
}