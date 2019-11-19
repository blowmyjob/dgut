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

    @RequestMapping("/toUserCharts")
    public String toUserCharts() {
        return "charts/toUserChart";
    }

    @RequestMapping("/toCompanyCharts")
    public String toCompanyCharts(){
        return "charts/toCompanyChart";
    }

    @RequestMapping("/UserCharts")
    public String userCharts(HttpServletRequest request,Model model){
        String time = request.getParameter("time");
        String state = request.getParameter("state");
        String chartType = request.getParameter("chartType");
        if("year".equals(time)){
            String year = request.getParameter("year");
            model.addAttribute("year",year);
        }else if("month".equals(time)){
            String month = request.getParameter("month");
            model.addAttribute("month",month);
        }
        model.addAttribute("time",time);
        model.addAttribute("state",state);
        if("1".equals(chartType)){
            return "charts/charts-1";
        }else if("2".equals(chartType)){
            return "charts/charts-2";
        }else if("3".equals(chartType)){
            return "charts/charts-3";
        }
        return "";
    }

    @RequestMapping("/CompanyCharts")
    public String CompanyCharts(HttpServletRequest request,Model model){
        String time = request.getParameter("time");
        String state = request.getParameter("state");
        String chartType = request.getParameter("chartType");
        String userid = (String)request.getSession().getAttribute("userid");
        if("year".equals(time)){
            String year = request.getParameter("year");
            model.addAttribute("year",year);
        }else if("month".equals(time)){
            String month = request.getParameter("month");
            model.addAttribute("month",month);
        }
        model.addAttribute("time",time);
        model.addAttribute("state",state);
        if("1".equals(chartType)){
            return "charts/charts-1";
        }else if("2".equals(chartType)){
            return "charts/charts-2";
        }else if("3".equals(chartType)){
            return "charts/charts-3";
        }
        return "";
    }

    @RequestMapping("/Charts")
    @ResponseBody
    public String ChartsByMonth(HttpServletRequest request){
        String type = request.getParameter("type");
        String time = request.getParameter("time");
        if("user".equals(type)) {
            if("year".equals(time)) {
                Integer userid = (Integer)request.getSession().getAttribute("userid");
                Integer year = Integer.valueOf(request.getParameter("year"));
                String state = request.getParameter("state");
                String charts = staticsService.ChartUserByYear(userid, year, state);
                return charts;
            }else if("month".equals(time)){
                Integer userid = (Integer)request.getSession().getAttribute("userid");
                Integer month = Integer.valueOf(request.getParameter("month"));
                String state = request.getParameter("state");
                String charts = "";
                if(state.equals("1")){
                    charts = staticsService.ChartUserByMonth(userid, month, "待查看");
                }else if(state.equals("2")){
                    charts = staticsService.ChartUserByMonth(userid, month, "待入职");
                }else if(state.equals("3")){
                    charts = staticsService.ChartUserByMonth(userid, month, "待面试");
                }else if(state.equals("4")){
                    charts = staticsService.ChartUserByMonth(userid, month, "待沟通");
                }else if(state.equals("5")){
                    charts = staticsService.ChartUserByMonth(userid, month, "不合适");
                }
                return charts;
            }
        }else if("company".equals(type)){
            Integer userid = (Integer)request.getSession().getAttribute("userid");
            Integer companyid = staticsService.getCompanyId(userid);
            if("year".equals(time)) {
                Integer year = Integer.valueOf(request.getParameter("year"));
                String charts = staticsService.ChartCompanyByYear(companyid,year);
                return charts;
            }else if("month".equals(time)){
                Integer month = Integer.valueOf(request.getParameter("month"));
                String charts = staticsService.ChartCompanyByMonth(companyid,month);
                return charts;
            }
        }
        return "";
    }
}
