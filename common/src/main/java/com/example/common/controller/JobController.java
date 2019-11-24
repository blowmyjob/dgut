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
        List<WorkDetail>workDetails = workService.getWorkDetailsByUserId(Integer.valueOf(userid),state);
        model.addAttribute("workDetails",workDetails);
        model.addAttribute("count",workDetails.size());
        return "hire/hire-list";
    }

    @GetMapping("/hr/{state}")
    public String getWorkStateByHr(@PathVariable("state")String state,HttpServletRequest request,Model model){
        try{
            Integer userid = (Integer)request.getSession().getAttribute("userid");
            Integer companyid = workService.findCompanyIdByUserId(userid);
            List<WorkDetail>workDetails = workService.getWorkDetailsByCompanyId(companyid,state);
            model.addAttribute("workDetails",workDetails);
            model.addAttribute("count",workDetails.size());
            return "user/hire-list";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "500";
    }

    @PostMapping("/hr/{state}/{id}")
    @ResponseBody
    public String updateState(@PathVariable("state")String state,@PathVariable("id")String id,HttpServletRequest request,Model model){
        try{
            String newState = request.getParameter("newState");
            workService.updateProcess(newState,Integer.valueOf(id));
            return "200";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "500";
    }

    @DeleteMapping("/user/{id}")
    @ResponseBody
    public String delWorkprocessByUser(@PathVariable("id")String id){
        try{
            workService.delProcessByUser(Integer.valueOf(id));
            return "200";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "500";
    }
    @DeleteMapping("/{id}")
    @ResponseBody
    public String delWorkprocessByHr(@PathVariable("id")String id){
        try {
            workService.delProcessByHr(Integer.valueOf(id));
            return "200";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "500";
    }
}
