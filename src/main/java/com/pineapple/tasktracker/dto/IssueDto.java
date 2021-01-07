package com.pineapple.tasktracker.dto;


import com.pineapple.tasktracker.model.Issue;
import com.pineapple.tasktracker.model.User;
import com.pineapple.tasktracker.model.enums.IssueStatus;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class IssueDto {
    private Long projectId;
    private String name;
    private String description;
    private List<Long> userIds;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private String issueStatus;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date started;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date finished;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date deadline;
    private List<Issue> childIssues;
}
