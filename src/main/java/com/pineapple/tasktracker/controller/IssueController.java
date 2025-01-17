package com.pineapple.tasktracker.controller;

import com.pineapple.tasktracker.dto.CommentDto;
import com.pineapple.tasktracker.dto.IssueDto;
import com.pineapple.tasktracker.dto.ProjectDto;
import com.pineapple.tasktracker.model.*;
import com.pineapple.tasktracker.model.enums.IssuePriority;
import com.pineapple.tasktracker.model.enums.IssueSeverity;
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
    private final ProjectParticipantRepository projectParticipantRepository;

    @GetMapping(value = "/issue/{id}")
    public String projects(Model model, @PathVariable long id)
    {
        Issue issue = issueRepository.findById(id).orElseThrow();
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
        model.addAttribute("statusList", new IssueStatus[] {IssueStatus.TO_DO, IssueStatus.IN_PROGRESS, IssueStatus.READY_FOR_TESTING, IssueStatus.COMPLETE});
        model.addAttribute("priorityList", new IssuePriority[] {IssuePriority.LOW, IssuePriority.MEDIUM, IssuePriority.HIGH, IssuePriority.CRITICAL});
        model.addAttribute("severityList", new IssueSeverity[] {IssueSeverity.LOW, IssueSeverity.MODERATE, IssueSeverity.MAJOR, IssueSeverity.CRITICAL});

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
        boolean flag = true;
        if (IssueStatus.valueOf(status) == IssueStatus.COMPLETE)
        {
            for (Issue childIssue : issue.getChildIssues())
            {
                if (childIssue.getIssueStatus() != IssueStatus.COMPLETE)
                {
                    flag = false;
                }
            }
        }
        if (flag)
        {
            issue.setIssueStatus(IssueStatus.valueOf(status));
            issueRepository.save(issue);
        }

        return "redirect:/issue/{id}";
    }

    @PostMapping(value = "/issue/{id}/edit-deadline")
    public String changeIssueDeadline(@ModelAttribute IssueDto issueDto, @PathVariable Long id) {
        Issue issue = issueRepository.findById(id).orElseThrow();
        if (!(issueDto.getDeadline() == null))
        {
            issue.setDeadline(new Timestamp(issueDto.getDeadline().getTime()));
        } else {
            issue.setDeadline(null);
        }
        issueRepository.save(issue);
        return "redirect:/issue/{id}";
    }

    @PostMapping(value = "/issue/{id}/edit-finished")
    public String changeIssueFinished(@ModelAttribute IssueDto issueDto, @PathVariable Long id) {
        Issue issue = issueRepository.findById(id).orElseThrow();
        if (!(issueDto.getFinished() == null)) {
            issue.setFinished(new Timestamp(issueDto.getFinished().getTime()));
        } else {
            issue.setFinished(null);
        }
        issueRepository.save(issue);
        return "redirect:/issue/{id}";
    }

    @PostMapping(value = "/issue/{id}/edit-started")
    public String changeIssueStarted(@ModelAttribute IssueDto issueDto, @PathVariable Long id) {
        Issue issue = issueRepository.findById(id).orElseThrow();
        if (!(issueDto.getStarted() == null)) {
            issue.setStarted(new Timestamp(issueDto.getStarted().getTime()));
        }
        else {
            issue.setStarted(null);
        }
        issueRepository.save(issue);

        return "redirect:/issue/{id}";
    }

    @PostMapping(value = "/issue/{id}/edit-priority")
    public String changeIssuePriority(@PathVariable Long id, @RequestParam String priority) {
        Issue issue = issueRepository.findById(id).orElseThrow();
        if (!priority.isEmpty()) {
            issue.setIssuePriority(IssuePriority.valueOf(priority));
        }
        else {
            issue.setIssuePriority(null);
        }

        issueRepository.save(issue);
        return "redirect:/issue/{id}";
    }

    @PostMapping(value = "/issue/{id}/edit-severity")
    public String changeIssueSeverity(@PathVariable Long id, @RequestParam String severity) {
        Issue issue = issueRepository.findById(id).orElseThrow();
        if (!severity.isEmpty()) {
            issue.setIssueSeverity(IssueSeverity.valueOf(severity));
        } else {
            issue.setIssueSeverity(null);
        }
        issueRepository.save(issue);

        return "redirect:/issue/{id}";
    }

    @PostMapping(value = "/issue/{id}/edit-assignees")
    public String changeIssueAssignees(@ModelAttribute IssueDto issueDto, @PathVariable Long id) {
        Issue issue = issueRepository.findById(id).orElseThrow();
        List<ProjectParticipant> issueProjectParticipants = new ArrayList<ProjectParticipant>();
        List<Long> selectedAssignees = issueDto.getUserIds();
        if(selectedAssignees == null) {
            issue.setProjectParticipants(null);
        } else {
            for (Long participantId: selectedAssignees)
            {
                ProjectParticipant participant = projectParticipantRepository.findById(participantId).orElseThrow();
                if (!issueProjectParticipants.contains(participant))
                {
                    issueProjectParticipants.add(participant);
                }
            }
            issue.setProjectParticipants(issueProjectParticipants);
        }
        issueRepository.save(issue);
        return "redirect:/issue/{id}";
    }

    @PostMapping(value = "/issue/{id}/edit-childIssues")
    public String changeIssueChildIssues(@ModelAttribute IssueDto issueDto, @PathVariable Long id) {
        Issue issue = issueRepository.findById(id).orElseThrow();
        List<Issue> childIssues = issue.getChildIssues();
        for (Issue childIssue: issueDto.getChildIssues())
        {
            if ((!childIssues.contains(childIssue)) && (!issue.equals(childIssue))) {
                childIssues.add(childIssue);
                childIssue.setParentIssue(issue);
                issueRepository.save(childIssue);
            }
        }
        issue.setChildIssues(childIssues);
        issueRepository.save(issue);
        return "redirect:/issue/{id}";
    }

    @PostMapping(value = "/issue/{id}/delete-issuelink")
    public String deleteChildIssue(@PathVariable Long id, @RequestParam String subIssueId) {
        Issue issue = issueRepository.findById(id).orElseThrow();
        Issue subIssue = issueRepository.findById(Long.valueOf(subIssueId)).orElseThrow();
        List<Issue> childIssues = issue.getChildIssues();

        childIssues.remove(subIssue);
        if (subIssue.getParentIssue().equals(issue)) {
            subIssue.setParentIssue(null);
        }
        issueRepository.save(issue);
        issueRepository.save(subIssue);
        return "redirect:/issue/{id}";
    }
}
