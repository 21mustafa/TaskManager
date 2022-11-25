package com.javaproject.taskmanager.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.javaproject.taskmanager.domain.ProjectUser;
import com.javaproject.taskmanager.domain.Task;
import com.javaproject.taskmanager.repository.ProjectUserRepository;
import com.javaproject.taskmanager.repository.TaskRepository;

@Controller
public class ProjectUserController {

    ProjectUserController() {

    }

    @Autowired
    HttpSession session;

    @Autowired
    ProjectUserRepository projectUserRepository;

    @Autowired
    TaskRepository taskRepository;

    @GetMapping("/login")
    public String showLogin(Model model) {
        model.addAttribute("user", new ProjectUser());
        return "projectUser/login";
    }

    @PostMapping("/login")
    public String login(Model model, ProjectUser user, HttpServletRequest request) {
        ProjectUser loginUser = projectUserRepository.findOneByEmailAndPassword(user.getEmail(), user.getPassword());
        if (loginUser != null) {
            session.invalidate();
            session = request.getSession();
            session.setAttribute("user", loginUser);
            model.addAttribute("user", loginUser);
            List<Task> taskList = taskRepository.findByUserAndTaskStatusOrderByTaskDeadline(loginUser, 0);
            model.addAttribute("mytasks", taskList);
            return "home/home";
            // return "redirect:/";
        }
        return "redirect:/login";
    }

    @GetMapping("/signup")
    public String showSignup(Model model) {
        model.addAttribute("user", new ProjectUser());
        return "projectUser/signup";
    }

    @PostMapping("/signup")
    public String signup(Model model, ProjectUser user) {
        projectUserRepository.save(user);
        return "redirect:/login";
    }

    @GetMapping("/signout")
    public String signout() {
        session.invalidate();
        // session.removeAttribute("user");
        return "redirect:/login";
    }

}