package com.javaproject.taskmanager.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private long id;

    @Column(name = "project_name")
    private String name;

    @Column(name = "project_description")
    private String description;

    @Column(name = "project_status")
    private int status;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Task> taskList  = new ArrayList<>();

    @ManyToMany(mappedBy = "projectList", cascade = CascadeType.ALL)
    private List<ProjectUser> userList  = new ArrayList<>();

    public Project(long id, String name, String description, int status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public Project() {
   
    }

    // public String getName() {
    //     return name;
    // }

    // public String getDescription() {
    //     return description;
    // }

    // public String getStatus() {
    //     return status;
    // }

    // public void setStatus(String status) {
    //     this.status = status;
    // }

    // public void setDescription(String description) {
    //     this.description = description;
    // }

    // public void setName(String name) {
    //     this.name = name;
    // }
}