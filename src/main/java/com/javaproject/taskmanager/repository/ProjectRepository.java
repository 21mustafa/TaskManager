package com.javaproject.taskmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.javaproject.taskmanager.domain.Project;

public interface ProjectRepository extends CrudRepository<Project, Long>{

    @Override
    public List<Project> findAll();

    @Query(nativeQuery=true, value="SELECT j.project_id, p.project_name, p.project_description, p.project_status FROM user_project j LEFT JOIN project p ON j.project_id = p.project_id WHERE user_id = :userId;")
    public List<Project> findAllProjectsByUserId(@Param("userId") long userId);

}