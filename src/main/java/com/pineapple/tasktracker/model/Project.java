package com.pineapple.tasktracker.model;

import com.pineapple.tasktracker.model.enums.IssueStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Setter
@Getter
@Table
@Entity
public class Project extends AbstractEntity {
    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Timestamp created;

    @Column
    private Timestamp started;

    @Column
    private Timestamp deadline;

    @Column
    private Timestamp finished;

    @OneToMany(mappedBy = "issueProject", cascade = CascadeType.REMOVE)
    private List<Issue> issues;

    @OneToMany(mappedBy = "project", cascade = CascadeType.REMOVE)
    private List<ProjectParticipant> projectParticipants;

    public long getNotCompletedTasksCount() {
        return issues.stream()
                .filter(issue -> issue.getIssueStatus() != IssueStatus.COMPLETE)
                .count();
    }
}