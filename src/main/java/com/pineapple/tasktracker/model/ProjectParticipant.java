package com.pineapple.tasktracker.model;

import com.pineapple.tasktracker.model.enums.ProjectRole;
import lombok.*;

import javax.persistence.*;

@Table
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

	@Override
	public boolean equals(Object o)
	{
		if (o instanceof ProjectParticipant)
		{
			return this.getId().equals(((ProjectParticipant) o).getId());
		}
		return false;
	}
}