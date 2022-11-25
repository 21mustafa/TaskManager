package com.javaproject.taskmanager.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaproject.taskmanager.domain.Project;
import com.javaproject.taskmanager.domain.ProjectUser;
import com.javaproject.taskmanager.repository.ProjectRepository;
import com.javaproject.taskmanager.repository.ProjectUserRepository;

@Controller
@RequestMapping("/project")
public class ProjectController {

   @Autowired
   HttpSession session;

   @Autowired
   ProjectRepository projectRepository;

   @Autowired
   ProjectUserRepository projectUserRepository;

   @GetMapping("/home")
   public String showProject(Model model) {
         ProjectUser user = (ProjectUser) session.getAttribute("user");
         model.addAttribute("user", user);
         List<Project> projects = projectRepository.findAllProjectsByUserId(user.getUserId());
         model.addAttribute("projects", projects);
         return "project/project";
   }

   @GetMapping("/new")
   public String displayProjectFrom(Model model) {
      model.addAttribute("user", session.getAttribute("user"));
      model.addAttribute("project", new Project());
      List<ProjectUser> users = projectUserRepository.findAll();
      model.addAttribute("users", users);
      return "project/new-project";
   }

   @PostMapping("/save")
   public String saveProject(Project project, Model model) {
      projectRepository.save(project);
      return "redirect:/project/home";
   }

   @GetMapping("/edit/{id}")
   public String editProject(@PathVariable Long id, Model model) {
      model.addAttribute("user", session.getAttribute("user"));
      Project project = projectRepository.findById(id).get();
      model.addAttribute("project", project);
      List<ProjectUser> users = projectUserRepository.findAll();
      model.addAttribute("users", users);
      return "project/edit-project";
   }

   @GetMapping("/delete/{id}")
   public String deleteTask(@PathVariable Long id) {
      projectRepository.deleteById(id);
      return "redirect:/project/home";
   }

   @GetMapping("/complete/{id}")
   public String completeTask(@PathVariable Long id) {
      Project project = projectRepository.findById(id).get();
      project.setStatus(1);
      projectRepository.save(project);
      return "redirect:/project/home";
   }

   @PostMapping("/update")
   public String addTask(Project project, Model model) {
      projectRepository.save(project);
      return "redirect:/project/home";
   }
}
