package com.pineapple.tasktracker.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Table
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IssueComment extends AbstractEntity{

    @Column
    private String text;

    @Column
    private Timestamp created;

    @Column
    private Timestamp lastUpdated;


    @ManyToOne
    @JoinColumn(name = "issue_id")
    private Issue issue;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User author;
}
