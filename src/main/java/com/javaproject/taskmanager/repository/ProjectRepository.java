package com.javaproject.taskmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.javaproject.taskmanager.domain.Project;

public interface ProjectRepository extends CrudRepository<Project, Long>{

    @Override
    public List<Project> findAll();

    @Query(nativeQuery=true, value="SELECT p.* FROM project p RIGHT JOIN user_project j ON p.project_id = j.project_id WHERE j.user_id = :userId")
    public List<Project> findAllProjectsByUserId(@Param("userId") long userId);

}