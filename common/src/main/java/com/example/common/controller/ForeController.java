package com.example.common.controller;

import com.example.common.entity.Hire;
import com.example.common.service.DataDictServcie;
import com.example.common.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @RequestMapping("")
    public String index(Model model){
        List<Hire>hires = workService.selectAllJob();
        model.addAttribute("hires",hires);
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
        model.addAttribute("hires",hires);
        return "front/search";
    }
}
