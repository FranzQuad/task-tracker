package com.pineapple.tasktracker.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the projectparticipant database table.
 * 
 */
@Entity
@NamedQuery(name="Projectparticipant.findAll", query="SELECT p FROM Projectparticipant p")
public class Projectparticipant implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	private Timestamp joined;

	private Timestamp left;

	//bi-directional many-to-one association to Issueparticipant
	@OneToMany(mappedBy="projectparticipant")
	private List<Issueparticipant> issueparticipants;

	//bi-directional many-to-one association to Project
	@ManyToOne
	@JoinColumn(name="project")
	private Project projectBean;

	//bi-directional many-to-one association to Projectrole
	@ManyToOne
	@JoinColumn(name="project_role")
	private Projectrole projectrole;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="user")
	private User userBean;

	public Projectparticipant() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getJoined() {
		return this.joined;
	}

	public void setJoined(Timestamp joined) {
		this.joined = joined;
	}

	public Timestamp getLeft() {
		return this.left;
	}

	public void setLeft(Timestamp left) {
		this.left = left;
	}

	public List<Issueparticipant> getIssueparticipants() {
		return this.issueparticipants;
	}

	public void setIssueparticipants(List<Issueparticipant> issueparticipants) {
		this.issueparticipants = issueparticipants;
	}

	public Issueparticipant addIssueparticipant(Issueparticipant issueparticipant) {
		getIssueparticipants().add(issueparticipant);
		issueparticipant.setProjectparticipant(this);

		return issueparticipant;
	}

	public Issueparticipant removeIssueparticipant(Issueparticipant issueparticipant) {
		getIssueparticipants().remove(issueparticipant);
		issueparticipant.setProjectparticipant(null);

		return issueparticipant;
	}

	public Project getProjectBean() {
		return this.projectBean;
	}

	public void setProjectBean(Project projectBean) {
		this.projectBean = projectBean;
	}

	public Projectrole getProjectrole() {
		return this.projectrole;
	}

	public void setProjectrole(Projectrole projectrole) {
		this.projectrole = projectrole;
	}

	public User getUserBean() {
		return this.userBean;
	}

	public void setUserBean(User userBean) {
		this.userBean = userBean;
	}

}