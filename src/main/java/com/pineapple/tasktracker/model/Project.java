package com.pineapple.tasktracker.model;

import com.pineapple.tasktracker.model.enums.IssueStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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

    @OneToMany(mappedBy = "issueProject")
    private List<Issue> issues;

    @OneToMany(mappedBy = "project")
    private List<ProjectParticipant> projectParticipants;

    public long getNotCompletedTasksCount() {
        return issues.stream()
                .filter(issue -> issue.getIssueStatus() != IssueStatus.COMPLETE)
                .count();
    }
}