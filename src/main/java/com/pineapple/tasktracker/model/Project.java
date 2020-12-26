package com.pineapple.tasktracker.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the project database table.
 * 
 */
@Entity
@NamedQuery(name="Project.findAll", query="SELECT p FROM Project p")
public class Project implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	private Timestamp closed;

	@Column(name="created_by")
	private Long createdBy;

	private String name;

	private Timestamp started;

	//bi-directional many-to-one association to Board
	@OneToMany(mappedBy="projectBean")
	private List<Board> boards;

	//bi-directional many-to-one association to Issue
	@OneToMany(mappedBy="projectBean")
	private List<Issue> issues;

	//bi-directional many-to-one association to Projectparticipant
	@OneToMany(mappedBy="projectBean")
	private List<Projectparticipant> projectparticipants;

	public Project() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getClosed() {
		return this.closed;
	}

	public void setClosed(Timestamp closed) {
		this.closed = closed;
	}

	public Long getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getStarted() {
		return this.started;
	}

	public void setStarted(Timestamp started) {
		this.started = started;
	}

	public List<Board> getBoards() {
		return this.boards;
	}

	public void setBoards(List<Board> boards) {
		this.boards = boards;
	}

	public Board addBoard(Board board) {
		getBoards().add(board);
		board.setProjectBean(this);

		return board;
	}

	public Board removeBoard(Board board) {
		getBoards().remove(board);
		board.setProjectBean(null);

		return board;
	}

	public List<Issue> getIssues() {
		return this.issues;
	}

	public void setIssues(List<Issue> issues) {
		this.issues = issues;
	}

	public Issue addIssue(Issue issue) {
		getIssues().add(issue);
		issue.setProjectBean(this);

		return issue;
	}

	public Issue removeIssue(Issue issue) {
		getIssues().remove(issue);
		issue.setProjectBean(null);

		return issue;
	}

	public List<Projectparticipant> getProjectparticipants() {
		return this.projectparticipants;
	}

	public void setProjectparticipants(List<Projectparticipant> projectparticipants) {
		this.projectparticipants = projectparticipants;
	}

	public Projectparticipant addProjectparticipant(Projectparticipant projectparticipant) {
		getProjectparticipants().add(projectparticipant);
		projectparticipant.setProjectBean(this);

		return projectparticipant;
	}

	public Projectparticipant removeProjectparticipant(Projectparticipant projectparticipant) {
		getProjectparticipants().remove(projectparticipant);
		projectparticipant.setProjectBean(null);

		return projectparticipant;
	}

}