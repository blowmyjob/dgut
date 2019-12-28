package com.example.common.controller;

import com.example.common.entity.Hire;
import com.example.common.service.DataDictServcie;
import com.example.common.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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
    public String search(Model model){
        List<Hire>hires = workService.selectAllJob();
        model.addAttribute("hires",hires);
        return "front/search";
    }
}
