package com.javaproject.taskmanager.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.javaproject.taskmanager.domain.ProjectUser;

public interface ProjectUserRepository extends CrudRepository<ProjectUser, Long> {

    ProjectUser findOneByEmailAndPassword(String email, String password);

    @Override
    List<ProjectUser> findAll();
}