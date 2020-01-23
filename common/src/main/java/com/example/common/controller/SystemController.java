package com.example.common.controller;

import com.example.common.entity.System;
import com.example.common.entity.SystemLog;
import com.example.common.service.SystemService;
import com.example.common.tools.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class SystemController {
    @Autowired
    private SystemService systemService;

    @GetMapping("/system-base")
    public String updateSystem1(Model model){
        model.addAttribute("system", SystemUtils.getSystem());
        return "system/system-base";
    }

    @PostMapping("/system-base")
    @ResponseBody
    public String updateSystem2(HttpServletRequest request,Model model){
        System newSystem = new System();
        newSystem.setWebsiteId(Integer.valueOf(request.getParameter("website-id")));
        newSystem.setWebsiteTitle(request.getParameter("website-title"));
        newSystem.setWebsiteDescription(request.getParameter("website-description"));
        newSystem.setWebsiteUploadfile(request.getParameter("website-uploadfile"));
        systemService.updateSystem(newSystem);
        SystemUtils.setSystem(newSystem);
        model.addAttribute("system",SystemUtils.getSystem());
        return "200";
    }

    @GetMapping("/system-log")
    public String selectLog(Model model){
        List<SystemLog>logs = systemService.getLogs();
        model.addAttribute("logs",logs);
        model.addAttribute("count",logs.size());
        return "system/system-log";
    }

    @GetMapping("/exception-log")
    public String exceptionLog(Model model){
        List<SystemLog>logs = systemService.getExceptionLogs();
        model.addAttribute("logs",logs);
        model.addAttribute("count",logs.size());
        return "system/system-log";
    }

    @GetMapping("/user/logs/{username}")
    public String logsByUser(@PathVariable(value = "username")String username, Model model){
        return "";
    }
}
