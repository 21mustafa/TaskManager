package com.javaproject.taskmanager.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Project {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long projectId;

    private String projectName;
    private String projectDescription;
    private int projectStatus;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Task> taskList  = new ArrayList<>();

    @ManyToMany(mappedBy = "projectList")
    private List<ProjectUser> userList  = new ArrayList<>();

}
