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
import com.javaproject.taskmanager.repository.ProjectRepository;

@Controller
@RequestMapping("/project")
public class ProjectController {

   @Autowired
   HttpSession session;

   @Autowired
   ProjectRepository projectRepository;

   @GetMapping("/home")
   public String showProject(Model model) {
         model.addAttribute("user", session.getAttribute("user"));
         List<Project> projects = projectRepository.findAll();
         model.addAttribute("projects", projects);
         return "project/project";
   }

   @GetMapping("/new")
   public String displayProjectFrom(Model model) {
      model.addAttribute("user", session.getAttribute("user"));
      model.addAttribute("project", new Project());
      return "project/new-project";
   }

   @PostMapping("/save")
   public String saveProject(Project project, Model model) {
      projectRepository.save(project);
      // System.out.println(project.getId());
      return "redirect:/project";
   }

   @GetMapping("/edit/{id}")
   public String editProject(@PathVariable Long id, Model model) {
      model.addAttribute("user", session.getAttribute("user"));
      Project project = projectRepository.findById(id).get();
      model.addAttribute("project", project);
      return "project/edit-project";
   }

   @GetMapping("/delete/{id}")
   public String deleteTask(@PathVariable Long id) {
      projectRepository.deleteById(id);
      return "redirect:/project";
   }

   @GetMapping("/complete/{id}")
   public String completeTask(@PathVariable Long id) {
      Project project = projectRepository.findById(id).get();
      project.setStatus(1);
      projectRepository.save(project);
      return "redirect:/project";
   }
}
