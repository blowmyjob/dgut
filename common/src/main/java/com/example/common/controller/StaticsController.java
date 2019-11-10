package com.example.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/charts")
public class StaticsController {
    @RequestMapping("/toCharts1")
    public String toCharts1(){
        return "charts/charts-1";
    }

    @RequestMapping("/Charts1")
    public String Charts1(){
        return null;
    }

    @RequestMapping("/toCharts2")
    public String toCharts2(){
        return "charts/charts-2";
    }

    @RequestMapping("/Charts2")
    public String Charts2(){
        return "";
    }

    @RequestMapping("/toCharts3")
    public String toCharts3(){
        return "charts/charts-3";
    }

    @RequestMapping("/Charts3")
    public String Charts3(){
        return "";
    }
}
