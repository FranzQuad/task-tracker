package com.pineapple.tasktracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pineapple.tasktracker.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByName(String name);
	User findByEmail(String name);
}