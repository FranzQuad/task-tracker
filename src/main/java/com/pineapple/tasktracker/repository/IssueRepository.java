package com.pineapple.tasktracker.repository;


import com.pineapple.tasktracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.pineapple.tasktracker.model.Issue;
import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {
    @Query("select i from Issue i where i.issueProject = " +
            "(select p from Project p where p =" +
                "(select pt.project from ProjectParticipant pt where pt.user = ?1)" +
            ")")
    List<Issue> findByUser(User user);
}
