package com.example.common.controller;

import com.example.common.entity.WorkProcess;
import com.example.common.service.WorkService;
import com.example.common.entity.WorkProcess;
import com.example.common.service.WorkService;
import com.example.common.vo.WorkDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/job")
public class JobController {
    @Autowired
    private WorkService workService;

    @GetMapping("/{state}")
    public String getWorkDetails(@PathVariable("state")String state, Model model, HttpServletRequest request){
        Integer userid = (Integer)request.getSession().getAttribute("userid");
        List<WorkDetail>workDetails = workService.getWorkDetails(Integer.valueOf(userid),state);
        model.addAttribute("workDetails",workDetails);
        return "hire/hire-list";
    }

    @PostMapping("/{id}/{state}")
    @ResponseBody
    public String updateWorkState(@PathVariable("id")String id,@PathVariable("state")String state,Model model){
        try{
            workService.updateProcess(state,Integer.valueOf(id));
            List<WorkProcess>workProcesses = workService.getProcessByUserId(Integer.valueOf(id),state);
            model.addAttribute("workProcesses",workProcesses);
            return "";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "500";
    }

    @PutMapping("")
    public String addWorkProcess(){
        return "";
    }


}
