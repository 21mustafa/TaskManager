package com.javaproject.taskmanager.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.javaproject.taskmanager.domain.ChartData;
import com.javaproject.taskmanager.domain.ProjectUser;
import com.javaproject.taskmanager.domain.Task;

public interface TaskRepository extends CrudRepository<Task, Long>{
    
    List<Task> findByUserAndTaskStatusOrderByTaskDeadline(ProjectUser user, int taskStatus);

    Task findOneByTaskId(Long id);

    @Query(nativeQuery=true, value="SELECT task_deadline AS taskDeadline, COUNT(task_id) AS graphValue FROM task WHERE user_id = :userId AND task_status = :taskStatus AND task_deadline BETWEEN :startDate AND :endDate GROUP BY task_deadline ORDER BY task_deadline")
    List<ChartData> selectGraphData(@Param("userId") long userId,@Param("taskStatus") int taskStatus, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

}