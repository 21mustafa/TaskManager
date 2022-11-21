package com.javaproject.taskmanager.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.javaproject.taskmanager.domain.Project;
import com.javaproject.taskmanager.domain.Task;
import com.javaproject.taskmanager.repository.ProjectRepository;
import com.javaproject.taskmanager.repository.TaskRepository;

@Controller
public class HomeController {

    @Autowired
    HttpSession session;

    @Autowired
    TaskRepository taskRepository;
    
    @Autowired
    ProjectRepository projectRepository;

    @GetMapping("/")
    public String showHome(Model model) {
        // ProjectUser user = (ProjectUser) session.getAttribute("user");
        // model.addAttribute("user", user);
        // List<Task> taskList = taskRepository.findByUserAndTaskStatusOrderByTaskDeadline(user, 0);
        // model.addAttribute("mytasks", taskList);
        return "home/home";
    }

    @GetMapping("/project")
    public String showProject(Model model) {
        List<Project> projects = projectRepository.findAll();
        model.addAttribute("projects", projects);
        return "project/project";
    }
}
