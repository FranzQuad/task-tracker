package com.pineapple.tasktracker.repository;

import com.pineapple.tasktracker.model.ProjectParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectParticipantRepository extends JpaRepository<ProjectParticipant, Long> {}
