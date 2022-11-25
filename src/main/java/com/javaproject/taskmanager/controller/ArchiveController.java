package com.javaproject.taskmanager.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.javaproject.taskmanager.domain.ProjectUser;
import com.javaproject.taskmanager.domain.Task;
import com.javaproject.taskmanager.repository.TaskRepository;

@Controller
@RequestMapping("/archive")
public class ArchiveController {

  @Autowired
  TaskRepository taskRepository;

  @Autowired
    HttpSession session;

  @GetMapping("/list")
  public String showArchive(Model model){
    ProjectUser user = (ProjectUser) session.getAttribute("user");
    model.addAttribute("user", session.getAttribute("user"));
    List<Task> taskList = taskRepository.findByUserAndTaskStatusOrderByTaskDeadline(user, 1);
    model.addAttribute("mytasks", taskList);
    return "archive/archive";
  }

  @GetMapping("/back-to-task/{id}")
  public String backToTask(@PathVariable Long id,Model model){
    Task task = taskRepository.findOneByTaskId(id);
    task.setTaskStatus(0);
    task.setTaskCompletedDate(null);
    taskRepository.save(task);
    return "redirect:/archive/list";
  }

  @GetMapping("/detail/{id}")
  public String showDetail(@PathVariable Long id, Model model){
    model.addAttribute("user", session.getAttribute("user"));
    Task task = taskRepository.findOneByTaskId(id);
    model.addAttribute("task", task);
    return "archive/detail";
  }

}
