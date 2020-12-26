package com.pineapple.tasktracker.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the issue database table.
 * 
 */
@Entity
@NamedQuery(name="Issue.findAll", query="SELECT i FROM Issue i")
public class Issue implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	private Timestamp finished;

	private String name;

	private Timestamp started;

	private Integer stauts;

	//bi-directional many-to-one association to Board
	@ManyToOne
	@JoinColumn(name="board")
	private Board boardBean;

	//bi-directional many-to-one association to Issuetype
	@ManyToOne
	@JoinColumn(name="type")
	private Issuetype issuetype;

	//bi-directional many-to-one association to Project
	@ManyToOne
	@JoinColumn(name="project")
	private Project projectBean;

	//bi-directional many-to-one association to Issuelabel
	@OneToMany(mappedBy="issueBean")
	private List<Issuelabel> issuelabels;

	//bi-directional many-to-many association to Issue
	@ManyToMany
	@JoinTable(
		name="issuetoissuelink"
		, joinColumns={
			@JoinColumn(name="issue2")
			}
		, inverseJoinColumns={
			@JoinColumn(name="issue1")
			}
		)
	private List<Issue> issues1;

	//bi-directional many-to-many association to Issue
	@ManyToMany(mappedBy="issues1")
	private List<Issue> issues2;

	public Issue() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getFinished() {
		return this.finished;
	}

	public void setFinished(Timestamp finished) {
		this.finished = finished;
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

	public Integer getStauts() {
		return this.stauts;
	}

	public void setStauts(Integer stauts) {
		this.stauts = stauts;
	}

	public Board getBoardBean() {
		return this.boardBean;
	}

	public void setBoardBean(Board boardBean) {
		this.boardBean = boardBean;
	}

	public Issuetype getIssuetype() {
		return this.issuetype;
	}

	public void setIssuetype(Issuetype issuetype) {
		this.issuetype = issuetype;
	}

	public Project getProjectBean() {
		return this.projectBean;
	}

	public void setProjectBean(Project projectBean) {
		this.projectBean = projectBean;
	}

	public List<Issuelabel> getIssuelabels() {
		return this.issuelabels;
	}

	public void setIssuelabels(List<Issuelabel> issuelabels) {
		this.issuelabels = issuelabels;
	}

	public Issuelabel addIssuelabel(Issuelabel issuelabel) {
		getIssuelabels().add(issuelabel);
		issuelabel.setIssueBean(this);

		return issuelabel;
	}

	public Issuelabel removeIssuelabel(Issuelabel issuelabel) {
		getIssuelabels().remove(issuelabel);
		issuelabel.setIssueBean(null);

		return issuelabel;
	}

	public List<Issue> getIssues1() {
		return this.issues1;
	}

	public void setIssues1(List<Issue> issues1) {
		this.issues1 = issues1;
	}

	public List<Issue> getIssues2() {
		return this.issues2;
	}

	public void setIssues2(List<Issue> issues2) {
		this.issues2 = issues2;
	}

}