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
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.xml.stream.events.Comment;
import java.sql.Time;
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
        model.addAttribute("username", username);

        return "site/issue";//here your name of your view (html)
    }

    @PostMapping(value = "/issue/{id}/add-comment")
    public String addIssueForProject(@ModelAttribute CommentDto commentDto, @PathVariable long id) {
        System.out.println(commentDto.getText());
        Issue issue = issueRepository.findById(id).orElseThrow();

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        User user = userRepository.findByName(username).orElseThrow();

        IssueComment comment = new IssueComment();
        comment.setCreated(new Timestamp(new Date().getTime()));
        comment.setText(commentDto.getText());
        comment.setIssue(issue);
        comment.setAuthor(user);
        issueCommentRepository.save(comment);

        return "redirect:/issue/{id}";
    }

    @PostMapping(value = "/issue/{id}/edit-description")
    public String changeIssueDescription(@PathVariable Long id, @RequestParam String description) {
        Issue issue = issueRepository.findById(id).orElseThrow();
        issue.setDescription(description);
        issueRepository.save(issue);
        return "redirect:/issue/{id}";
    }

    @PostMapping(value = "/issue/{id}/edit-name")
    public String changeIssueName(@PathVariable Long id, @RequestParam String name) {
        Issue issue = issueRepository.findById(id).orElseThrow();
        issue.setName(name);
        issueRepository.save(issue);
        return "redirect:/issue/{id}";
    }

    @PostMapping(value = "/issue/{id}/edit-status")
    public String changeIssueStatus(@PathVariable Long id, @RequestParam String status) {
        Issue issue = issueRepository.findById(id).orElseThrow();
        issue.setIssueStatus(IssueStatus.valueOf(status));
        issueRepository.save(issue);
        return "redirect:/issue/{id}";
    }

    @PostMapping(value = "/issue/{id}/edit-deadline")
    public String changeIssueDeadline(@ModelAttribute IssueDto issueDto, @PathVariable Long id) {
        Issue issue = issueRepository.findById(id).orElseThrow();
        issue.setDeadline(new Timestamp(issueDto.getDeadline().getTime()));
        issueRepository.save(issue);
        return "redirect:/issue/{id}";
    }

    @PostMapping(value = "/issue/{id}/edit-finished")
    public String changeIssueFinished(@ModelAttribute IssueDto issueDto, @PathVariable Long id) {
        Issue issue = issueRepository.findById(id).orElseThrow();
        issue.setFinished(new Timestamp(issueDto.getFinished().getTime()));
        issueRepository.save(issue);
        return "redirect:/issue/{id}";
    }

    @PostMapping(value = "/issue/{id}/edit-started")
    public String changeIssueStarted(@ModelAttribute IssueDto issueDto, @PathVariable Long id) {
        Issue issue = issueRepository.findById(id).orElseThrow();
        issue.setStarted(new Timestamp(issueDto.getStarted().getTime()));
        issueRepository.save(issue);
        return "redirect:/issue/{id}";
    }
}
