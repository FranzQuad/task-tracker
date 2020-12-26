package com.pineapple.tasktracker.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the issueparticipant database table.
 * 
 */
@Embeddable
public class IssueparticipantPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private Long issue;

	@Column(name="project_participant", insertable=false, updatable=false)
	private Long projectParticipant;

	@Column(name="issue_role", insertable=false, updatable=false)
	private Long issueRole;

	public IssueparticipantPK() {
	}
	public Long getIssue() {
		return this.issue;
	}
	public void setIssue(Long issue) {
		this.issue = issue;
	}
	public Long getProjectParticipant() {
		return this.projectParticipant;
	}
	public void setProjectParticipant(Long projectParticipant) {
		this.projectParticipant = projectParticipant;
	}
	public Long getIssueRole() {
		return this.issueRole;
	}
	public void setIssueRole(Long issueRole) {
		this.issueRole = issueRole;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof IssueparticipantPK)) {
			return false;
		}
		IssueparticipantPK castOther = (IssueparticipantPK)other;
		return 
			this.issue.equals(castOther.issue)
			&& this.projectParticipant.equals(castOther.projectParticipant)
			&& this.issueRole.equals(castOther.issueRole);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.issue.hashCode();
		hash = hash * prime + this.projectParticipant.hashCode();
		hash = hash * prime + this.issueRole.hashCode();
		
		return hash;
	}
}