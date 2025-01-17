package com.pineapple.tasktracker.model;

import com.pineapple.tasktracker.model.enums.IssuePriority;
import com.pineapple.tasktracker.model.enums.IssueSeverity;
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
	private Timestamp created;

	@Column
	private Timestamp started;

	@Column
	private Timestamp deadline;

	@Column
	private Timestamp finished;

	@Column
	private Timestamp redline;

	@Column
	private String name;

	@Column
	private String description;

	@OneToOne
	@JoinColumn(name = "reporter")
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

	@ManyToMany(mappedBy = "issues")
	private List<ProjectParticipant> projectParticipants;

	@JoinColumn(name = "project_id")
	@ManyToOne
	private Project issueProject;

	@Column
	@Enumerated(EnumType.STRING)
	private IssueStatus issueStatus;

	@Column
	@Enumerated(EnumType.STRING)
	private IssuePriority issuePriority;

	@Column
	@Enumerated(EnumType.STRING)
	private IssueSeverity issueSeverity;

	@Override
	public boolean equals(Object o)
	{
		if (o instanceof Issue)
		{
			return this.getId().equals(((Issue) o).getId());
		}
		return false;
	}
}