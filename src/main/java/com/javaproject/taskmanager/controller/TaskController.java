package com.javaproject.taskmanager.controller;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// import com.javaproject.taskmanager.domain.Project;
// import com.javaproject.taskmanager.domain.ProjectUser;
import com.javaproject.taskmanager.domain.Task;
// import com.javaproject.taskmanager.repository.ProjectRepository;
import com.javaproject.taskmanager.repository.TaskRepository;

@Controller
@RequestMapping("/task")
public class TaskController {

    @Autowired
    TaskRepository taskRepository;

    // @Autowired
    // ProjectRepository projectRepository;

    @Autowired
    HttpSession session;

    @GetMapping("/add")
    public String showAddTask(Model model) {
        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("task", new Task());
        // List<Project> projectList = projectRepository.findAll();
        // model.addAttribute("projects", projectList);
        return "task/add_task";
    }

    @PostMapping("/add")
    public String addTask(Task task) {
        // task.setUser((ProjectUser) session.getAttribute("user"));
        taskRepository.save(task);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editTask(@PathVariable Long id, Model model) {
        model.addAttribute("user", session.getAttribute("user"));
        Task task = taskRepository.findOneByTaskId(id);
        model.addAttribute("task", task);
        // List<Project> projectList = projectRepository.findAll();
        // model.addAttribute("projects", projectList);
        return "task/edit_task";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/complete/{id}")
    public String completeTask(@PathVariable Long id) {
        Task task = taskRepository.findOneByTaskId(id);
        task.setTaskStatus(1);
        long today = System.currentTimeMillis();
        Date date = new Date(today);
        task.setTaskCompletedDate(date);
        taskRepository.save(task);
        return "redirect:/";
    }

    @PostMapping("/update")
    public String addTask(Task task, Model model) {
        taskRepository.save(task);
        return "redirect:/";
    }

}
