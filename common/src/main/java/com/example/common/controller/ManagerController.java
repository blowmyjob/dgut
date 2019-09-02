package com.example.common.controller;

import com.example.common.entity.User;
import com.example.common.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ManagerController {
    @Autowired
    private UserService userService;

    @GetMapping("/Manager")
    @RequiresPermissions("manager:see")
    public String seeManager(Model model){
        List<User>managers = userService.getManagers();
        model.addAttribute("managers",managers);
        model.addAttribute("count",managers.size());
        return "user/admin-list";
    }

    @GetMapping("/Manageradd")
    @RequiresPermissions("manager:add")
    public String addManager(){
        return "user/admin-add";
    }
}
