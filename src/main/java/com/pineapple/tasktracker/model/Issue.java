package com.pineapple.tasktracker.model;

import com.pineapple.tasktracker.model.enums.IssueStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Setter
@Getter
@Table
public class Issue extends AbstractEntity {
	@Column
	private Timestamp finished;

	@Column
	private Timestamp started;

	@Column
	private Timestamp deadline;

	@Column
	private Timestamp redline;

	@Column
	private String name;

	@Column
	private String description;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User reporter;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_issue_id")
	private Issue parentIssue;

	@OneToMany(
			mappedBy = "parentIssue",
			cascade = CascadeType.REMOVE,
			orphanRemoval = true,
			fetch = FetchType.LAZY)
	private List<Issue> childIssues;

	@JoinTable(
			name = "issue_participant",
			joinColumns = @JoinColumn(name = "issue_id"),
			inverseJoinColumns = @JoinColumn(name = "participant_id"))
	@ManyToMany
	private List<ProjectParticipant> projectParticipants;

	@JoinColumn(name = "project_id")
	@ManyToOne
	private Project issueProject;

	@Column
	@Enumerated(EnumType.STRING)
	private IssueStatus issueStatus;
}