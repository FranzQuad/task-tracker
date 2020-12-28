package com.pineapple.tasktracker.repository;

import com.pineapple.tasktracker.model.Project;
import com.pineapple.tasktracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query("select p from Project p where p =" +
            "(select pt.project from ProjectParticipant pt where pt.user = ?1)")
    List<Project> findByUser(User user);
}