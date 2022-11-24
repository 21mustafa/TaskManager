package com.javaproject.taskmanager.domain;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Task {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long taskId;

    private String taskName;
    private String taskDescription;
    private int taskStatus;
    private Date taskDeadline;
    private Date taskCompletedDate = null;

    // @ManyToOne
    // @JoinColumn(name = "user_id")
    // private ProjectUser user;

    // @ManyToOne
    // @JoinColumn(name = "project_id")
    // private Project project;
}
