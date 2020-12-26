package com.pineapple.tasktracker.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	private String email;

	private String login;

	private String name;

	@Column(name="password_hash")
	private String passwordHash;

	//bi-directional many-to-one association to Projectparticipant
	@OneToMany(mappedBy="userBean")
	private List<Projectparticipant> projectparticipants;

	public User() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPasswordHash() {
		return this.passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public List<Projectparticipant> getProjectparticipants() {
		return this.projectparticipants;
	}

	public void setProjectparticipants(List<Projectparticipant> projectparticipants) {
		this.projectparticipants = projectparticipants;
	}

	public Projectparticipant addProjectparticipant(Projectparticipant projectparticipant) {
		getProjectparticipants().add(projectparticipant);
		projectparticipant.setUserBean(this);

		return projectparticipant;
	}

	public Projectparticipant removeProjectparticipant(Projectparticipant projectparticipant) {
		getProjectparticipants().remove(projectparticipant);
		projectparticipant.setUserBean(null);

		return projectparticipant;
	}

}