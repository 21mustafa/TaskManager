package com.javaproject.taskmanager.repository;

import org.springframework.data.repository.CrudRepository;

import com.javaproject.taskmanager.domain.ProjectUser;

public interface ProjectuserRepository extends CrudRepository<ProjectUser, Long>{
    
    ProjectUser findByEmailAndPassword(String email, String password);

}
