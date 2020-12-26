package com.pineapple.tasktracker.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the issuerole database table.
 * 
 */
@Entity
@NamedQuery(name="Issuerole.findAll", query="SELECT i FROM Issuerole i")
public class Issuerole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	private String name;

	//bi-directional many-to-one association to Issueparticipant
	@OneToMany(mappedBy="issuerole")
	private List<Issueparticipant> issueparticipants;

	public Issuerole() {
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

	public List<Issueparticipant> getIssueparticipants() {
		return this.issueparticipants;
	}

	public void setIssueparticipants(List<Issueparticipant> issueparticipants) {
		this.issueparticipants = issueparticipants;
	}

	public Issueparticipant addIssueparticipant(Issueparticipant issueparticipant) {
		getIssueparticipants().add(issueparticipant);
		issueparticipant.setIssuerole(this);

		return issueparticipant;
	}

	public Issueparticipant removeIssueparticipant(Issueparticipant issueparticipant) {
		getIssueparticipants().remove(issueparticipant);
		issueparticipant.setIssuerole(null);

		return issueparticipant;
	}

}