package com.javaproject.taskmanager.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.javaproject.taskmanager.domain.Project;

public interface ProjectRepository extends CrudRepository<Project, Long>{

    @Override
    public List<Project> findAll();

}