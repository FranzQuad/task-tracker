package com.pineapple.tasktracker.repository;

import com.pineapple.tasktracker.model.Project;
import com.pineapple.tasktracker.model.User;
import com.pineapple.tasktracker.model.ProjectParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectParticipantRepository extends JpaRepository<ProjectParticipant, Long> {

    ProjectParticipant findFirstByUserAndProject(User user, Project project);

    List<ProjectParticipant> findAllByProject(Project project);

    ProjectParticipant getFirstByUserId(Long userId);

    ProjectParticipant findFirstByUserIdAndProjectId(Long userId, Long projectId);
}


