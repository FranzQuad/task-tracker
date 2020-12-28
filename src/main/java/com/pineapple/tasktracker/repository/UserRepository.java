package com.pineapple.tasktracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pineapple.tasktracker.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByName(String name);
	Optional<User> findByEmail(String name);
}