package com.javaproject.taskmanager.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.javaproject.taskmanager.domain.ProjectUser;
import com.javaproject.taskmanager.repository.ProjectuserRepository;

@Controller
public class ProjectUserController {
    
    @Autowired
    HttpSession session;

    @Autowired
    ProjectuserRepository projectuserRepository;

    @GetMapping("/login")
    public String showLogin(Model model) {
        model.addAttribute("user", new ProjectUser());
        return "home/login";
    }

    @PostMapping("/login")
    public String login(Model model, ProjectUser user){
        ProjectUser loginUser = projectuserRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
        if (loginUser.getUserId() > 0) {
            session.setAttribute("user", loginUser);
            model.addAttribute("user", loginUser);
            return "redirect:/";
        }
        return "redirect:/login";
    }

}
