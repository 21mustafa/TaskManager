package com.javaproject.taskmanager.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.javaproject.taskmanager.domain.ChartData;
import com.javaproject.taskmanager.domain.ProjectUser;
import com.javaproject.taskmanager.repository.TaskRepository;

@Controller
public class GraphController {

    @Autowired
    HttpSession session;

    @Autowired
    TaskRepository taskRepository;
    
    @GetMapping("/graph")
    public String showGraph(Model model) {
        model.addAttribute("duration", "week");
        return "redirect:/graph/week";
    }

    @GetMapping("/graph/{duration}")
    public String showGraphWeek(Model model,@PathVariable String duration) {
        ProjectUser user = (ProjectUser) session.getAttribute("user");
        model.addAttribute("user", user);
        model.addAttribute("duration", duration);
        Calendar calendar = Calendar.getInstance();
        Date startDate = new Date(calendar.getTime().getTime());
        if (duration.equals("week")) {
            calendar.add(Calendar.DAY_OF_MONTH, 6);
        }else if (duration.equals("month")) {
            calendar.add(Calendar.MONTH, 1);
            calendar.add(Calendar.DAY_OF_MONTH, -1);
        }
        Date endDate = new Date(calendar.getTime().getTime());
        List<ChartData> chartDataList = taskRepository.selectGraphData(user.getUserId(), 0, startDate, endDate);
        List<String> labelList = new ArrayList<>();
        List<String> valueList = new ArrayList<>();
        for(ChartData data : chartDataList) {
            labelList.add(data.getTaskDeadline());
            valueList.add(data.getGraphValue());
        }
        model.addAttribute("labelList", labelList);
        model.addAttribute("valueList", valueList);
        return "graph/graph";
    }
}