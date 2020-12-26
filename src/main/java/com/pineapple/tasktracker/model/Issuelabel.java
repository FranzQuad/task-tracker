package com.pineapple.tasktracker.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the issuelabel database table.
 * 
 */
@Entity
@NamedQuery(name="Issuelabel.findAll", query="SELECT i FROM Issuelabel i")
public class Issuelabel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	private String color;

	private String name;

	//bi-directional many-to-one association to Issue
	@ManyToOne
	@JoinColumn(name="issue")
	private Issue issueBean;

	public Issuelabel() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Issue getIssueBean() {
		return this.issueBean;
	}

	public void setIssueBean(Issue issueBean) {
		this.issueBean = issueBean;
	}

}