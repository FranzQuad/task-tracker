package com.pineapple.tasktracker.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pineapple.tasktracker.model.Issue;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {
	
}
