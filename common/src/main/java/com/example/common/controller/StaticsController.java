package com.example.common.controller;

import com.example.common.entity.Statics;
import com.example.common.service.StaticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.security.jgss.HttpCaller;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/charts")
public class StaticsController {
    @Autowired
    private StaticsService staticsService;

    @RequestMapping("/toCharts1")
    public String toCharts1(Model model) {
        return "charts/charts-1";
    }

    @RequestMapping("/Charts1")
    @ResponseBody
    public String Charts1(HttpServletRequest request){
        Integer userid = Integer.valueOf(request.getParameter("userid"));
        Integer month = Integer.valueOf(request.getParameter("month"));
        String charts = staticsService.ChartUserByMonth(userid,month,"待查看");
        return charts;
    }

    @RequestMapping("/toCharts2")
    public String toCharts2(){
        return "charts/charts-2";
    }

    @RequestMapping("/Charts2")
    @ResponseBody
    public String Charts2(HttpServletRequest request){
        Integer userid = Integer.valueOf(request.getParameter("userid"));
        Integer month = Integer.valueOf(request.getParameter("month"));
        String charts = staticsService.ChartUserByMonth(userid,month,"待查看");
        return charts;
    }

    @RequestMapping("/toCharts3")
    public String toCharts3(){
        return "charts/charts-3";
    }

    @RequestMapping("/Charts3")
    @ResponseBody
    public String Charts3(HttpServletRequest request){
        Integer userid = Integer.valueOf(request.getParameter("userid"));
        Integer month = Integer.valueOf(request.getParameter("month"));
        String charts = staticsService.ChartUserByMonth(userid,month,"待查看");
        return charts;
    }
}
