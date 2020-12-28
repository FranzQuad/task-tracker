package com.pineapple.tasktracker.model;

import com.pineapple.tasktracker.model.enums.ProjectRole;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Table
@Setter
@Entity
@Builder
public class ProjectParticipant extends AbstractEntity {
	@Column
	@Enumerated(EnumType.STRING)
	private ProjectRole projectRole;

	@ManyToOne
	@JoinColumn(name = "project_id")
	private Project project;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
}