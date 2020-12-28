package com.pineapple.tasktracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pineapple.tasktracker.model.ProjectParticipant;


@Repository
public interface ProjectParticipantRepository extends JpaRepository<ProjectParticipant, Long> {}
