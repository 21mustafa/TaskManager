package com.javaproject.taskmanager.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.javaproject.taskmanager.domain.ProjectUser;
import com.javaproject.taskmanager.domain.Task;

public interface TaskRepository extends CrudRepository<Task, Long>{
    
    List<Task> findByUserAndTaskStatusOrderByTaskDeadline(ProjectUser user, int taskStatus);

    Task findOneByTaskId(Long id);

    

}
