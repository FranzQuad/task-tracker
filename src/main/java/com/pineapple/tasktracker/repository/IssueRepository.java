package com.pineapple.tasktracker.repository;


import com.pineapple.tasktracker.model.Project;
import com.pineapple.tasktracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.pineapple.tasktracker.model.Issue;
import java.util.List;
import java.util.Optional;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {
    @Query("select i from Issue i where i.issueProject in " +
            "(select p from Project p where p in" +
                "(select pt.project from ProjectParticipant pt where pt.user = ?1)" +
            ")")
    List<Issue> findByUser(User user);

    Optional<Issue> findByIdAndIssueProject(Long id, Project issueProject);
}
