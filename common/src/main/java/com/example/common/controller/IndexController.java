package com.example.common.controller;

import com.example.common.entity.SysRole;
import com.example.common.entity.System;
import com.example.common.entity.SystemLog;
import com.example.common.service.SystemService;
import com.example.common.service.UserService;
import com.example.common.tools.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {
    @Autowired
    private UserService userService;

    @Autowired
    private SystemService systemService;

    @RequestMapping("/index")
    public String index(){
        System system = systemService.selectSystem();
        SystemUtils.setSystem(system);
        return "index";
    }

    @RequestMapping("/welcome")
    public String welcome(HttpServletRequest request, Model model){
        String userName = (String) request.getSession().getAttribute("userName");
        Map<String,String>map = new HashMap<>();map.put("userName",userName);
        List<SystemLog>logs = systemService.getLogsByType(map);
        model.addAttribute("logs","logs");
        model.addAttribute("count",logs.size());
        if(logs.size()>2){
            model.addAttribute("log",logs.get(1));
        }
        return "welcome";
    }
}
