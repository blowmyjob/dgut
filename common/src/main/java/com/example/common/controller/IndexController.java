package com.example.common.controller;

import com.example.common.entity.SysRole;
import com.example.common.entity.System;
import com.example.common.service.SystemService;
import com.example.common.service.UserService;
import com.example.common.tools.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    public String welcome(){
        return "welcome";
    }
}
