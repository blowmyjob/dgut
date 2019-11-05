package com.example.webrtc.controller;

import com.example.webrtc.entity.WorkProcess;
import com.example.webrtc.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/job")
public class JobController {
    @Autowired
    private WorkService workService;

    @GetMapping("/{id}/{state}")
    public String getWorkProcess(@PathVariable("id")String id,@PathVariable("state")String state,Model model){
        List<WorkProcess>workProcesses = workService.getProcessByUserId(Integer.valueOf(id),state);
        model.addAttribute("workProcesses",workProcesses);
        return "";
    }

    @PostMapping("/{id}/{state}")
    @ResponseBody
    public String updateWorkState(@PathVariable("id")String id,@PathVariable("state")String state){
        try{
            workService.updateProcess(state,Integer.valueOf(id));
            return "200";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "500";
    }

}
