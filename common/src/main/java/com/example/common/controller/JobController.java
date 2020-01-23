package com.example.common.controller;

import com.example.common.entity.Hire;
import com.example.common.entity.Resume;
import com.example.common.entity.WorkProcess;
import com.example.common.service.UserService;
import com.example.common.service.WorkService;
import com.example.common.entity.WorkProcess;
import com.example.common.service.WorkService;
import com.example.common.vo.WorkDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/job")
public class JobController {
    @Autowired
    private WorkService workService;

    @Autowired
    private UserService userService;

    @GetMapping("/{state}")
    public String getWorkDetails(@PathVariable("state")String state, Model model, HttpServletRequest request){
        Integer userid = (Integer)request.getSession().getAttribute("userid");
        List<WorkDetail>workDetails = workService.getWorkDetailsByUserId(Integer.valueOf(userid),state);
        model.addAttribute("workDetails",workDetails);
        model.addAttribute("count",workDetails.size());
        return "user/hire-list";
    }

    @GetMapping("/hr/发布")
    public String toCreateJob(){
        return "hire/hire-new";
    }

    @PostMapping("/hr/发布")
    public String createJob(HttpServletRequest request,Model model){
        Integer userId = (Integer) request.getSession().getAttribute("userid");
        String hireName = request.getParameter("hireName");
        Integer hireCount = Integer.valueOf(request.getParameter("hireCount"));
        String hireDescription = request.getParameter("hireDescription");
        String hireEndTime = request.getParameter("hireEndTime");
        Integer companyId = workService.findCompanyIdByUserId(userId);
        Hire hire = new Hire();
        hire.setWorkname(hireName);hire.setHirecount(hireCount);
        hire.setCreatetime(new Timestamp(System.currentTimeMillis()));
        hire.setDescription(hireDescription);hire.setCompanyid(companyId);
        hire.setEndtime(Timestamp.valueOf(hireEndTime));
        workService.insertHire(hire);
        return "200";
    }

    @GetMapping("/hr/{state}")
    public String getWorkStateByHr(@PathVariable("state")String state,HttpServletRequest request,Model model){
        try{
            Integer userid = (Integer)request.getSession().getAttribute("userid");
            Integer companyid = workService.findCompanyIdByUserId(userid);
            List<WorkDetail>workDetails = workService.getWorkDetailsByCompanyId(companyid,state);
            model.addAttribute("workDetails",workDetails);
            model.addAttribute("count",workDetails.size());
            return "hire/hire-list";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "500";
    }

    @PostMapping("/hr/{state}/{id}")
    @ResponseBody
    public String updateState(@PathVariable("state")String state,@PathVariable("id")String id){
        try{
            workService.updateProcess(state,Integer.valueOf(id));
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

    @GetMapping("/apply/{id}/{companyid}")
    @ResponseBody
    public String apply(@PathVariable("id")Integer id,@PathVariable("companyid")Integer companyid, HttpServletRequest request){
        WorkProcess workProcess = new WorkProcess();
        Integer userId = (Integer)request.getSession().getAttribute("userid");
        workProcess.setUserid(userId);
        workProcess.setJobid(id);
        workProcess.setCompanyid(companyid);
        workService.insertProcess(workProcess);
        return "1";
    }
}
