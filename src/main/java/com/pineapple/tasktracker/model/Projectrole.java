package com.pineapple.tasktracker.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the projectrole database table.
 * 
 */
@Entity
@NamedQuery(name="Projectrole.findAll", query="SELECT p FROM Projectrole p")
public class Projectrole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	private String name;

	//bi-directional many-to-one association to Projectparticipant
	@OneToMany(mappedBy="projectrole")
	private List<Projectparticipant> projectparticipants;

	public Projectrole() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Projectparticipant> getProjectparticipants() {
		return this.projectparticipants;
	}

	public void setProjectparticipants(List<Projectparticipant> projectparticipants) {
		this.projectparticipants = projectparticipants;
	}

	public Projectparticipant addProjectparticipant(Projectparticipant projectparticipant) {
		getProjectparticipants().add(projectparticipant);
		projectparticipant.setProjectrole(this);

		return projectparticipant;
	}

	public Projectparticipant removeProjectparticipant(Projectparticipant projectparticipant) {
		getProjectparticipants().remove(projectparticipant);
		projectparticipant.setProjectrole(null);

		return projectparticipant;
	}

}