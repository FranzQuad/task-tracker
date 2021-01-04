package com.pineapple.tasktracker.repository;

import com.pineapple.tasktracker.model.Issue;
import com.pineapple.tasktracker.model.IssueComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueCommentRepository extends JpaRepository<IssueComment, Long> {
    List<IssueComment> findAllByIssue(Issue issue);
}
