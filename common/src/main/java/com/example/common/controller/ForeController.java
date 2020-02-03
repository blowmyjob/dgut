package com.example.common.controller;

import com.example.common.entity.Company;
import com.example.common.entity.Hire;
import com.example.common.entity.Resume;
import com.example.common.service.DataDictServcie;
import com.example.common.service.UserService;
import com.example.common.service.WorkService;
import com.example.common.tools.Tranfer;
import com.example.common.vo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/fore")
public class ForeController {
    @Autowired
    private DataDictServcie dataDictServcie;

    @Autowired
    private WorkService workService;

    @Autowired
    private UserService userService;

    @RequestMapping("")
    public String index(Model model){
        List<Hire>hires = workService.selectAllJob();
        List<Category>categories1 = workService.getCatesByCategory(new HashMap<>());
        List<Category>categories2 = workService.getCatesByLocation(new HashMap<>());
        Integer count = workService.getJobLastWeek();
        model.addAttribute("hires",hires);
        model.addAttribute("count",count);
        model.addAttribute("category1",categories1);
        model.addAttribute("category2",categories2);
        return "front/index";
    }

    @RequestMapping("/search")
    public String search(Model model, HttpServletRequest request){
        String keyword = request.getParameter("keyword");
        String area = request.getParameter("area");
        String category = request.getParameter("category");
        Map<String,String>map = new HashMap<>();
        if(!StringUtils.isEmpty(keyword))map.put("keyword",keyword);
        if(!(StringUtils.isEmpty(area)|| area.equals("all")))map.put("location",area);
        if(!(StringUtils.isEmpty(category)||category.equals("all")))map.put("category",category);
        List<Hire>hires = workService.searchJob(map);
        List<Category>category1 = workService.getCatesByCategory(map);
        List<Category>category2 = workService.getCatesByLocation(map);
        model.addAttribute("hires",hires);
        model.addAttribute("category1",category1);
        model.addAttribute("category2",category2);
        return "front/search";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id")String id, Model model, HttpServletRequest request){
        Map<String,String> map = new HashMap<>();
        map.put("id",id);
        String userName = (String)request.getSession().getAttribute("userName");
        List<Hire> job = workService.searchJob(map);
        List<Resume>resumes = userService.getResumes(userName);
        if(job.get(0).getRequirements()!= null) {
            List<String> requirements = Tranfer.transfer(job.get(0).getRequirements());
            model.addAttribute("requirements",requirements);
        }
        model.addAttribute("job",job.get(0));
        model.addAttribute("resumes",resumes);
        return "front/jobdetail";
    }
}
