package com.pineapple.tasktracker.model;

import com.pineapple.tasktracker.model.enums.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="users")
@Setter
@Getter
public class User extends AbstractEntity {
	@Column
	private String email;

	@Column
	private String login;

	@Column
	private String name;

	@Column
	private String password;

	@Column
	@Enumerated(EnumType.STRING)
	private Role role;

	@OneToMany(mappedBy = "user")
	private List<ProjectParticipant> participates;
}