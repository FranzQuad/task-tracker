package com.pineapple.tasktracker.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the issueparticipant database table.
 * 
 */
@Entity
@NamedQuery(name="Issueparticipant.findAll", query="SELECT i FROM Issueparticipant i")
public class Issueparticipant implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private IssueparticipantPK id;

	private Timestamp added;

	//bi-directional many-to-one association to Issuerole
	@ManyToOne
	@JoinColumn(name="issue_role", insertable = false, updatable = false)
	private Issuerole issuerole;

	//bi-directional many-to-one association to Projectparticipant
	@ManyToOne
	@JoinColumn(name="project_participant", insertable = false, updatable = false)
	private Projectparticipant projectparticipant;

	public Issueparticipant() {
	}

	public IssueparticipantPK getId() {
		return this.id;
	}

	public void setId(IssueparticipantPK id) {
		this.id = id;
	}

	public Timestamp getAdded() {
		return this.added;
	}

	public void setAdded(Timestamp added) {
		this.added = added;
	}

	public Issuerole getIssuerole() {
		return this.issuerole;
	}

	public void setIssuerole(Issuerole issuerole) {
		this.issuerole = issuerole;
	}

	public Projectparticipant getProjectparticipant() {
		return this.projectparticipant;
	}

	public void setProjectparticipant(Projectparticipant projectparticipant) {
		this.projectparticipant = projectparticipant;
	}

}