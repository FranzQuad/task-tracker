package com.pineapple.tasktracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pineapple.tasktracker.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
	
}
