package com.example.common.controller;

import com.example.common.entity.SysRole;
import com.example.common.entity.User;
import com.example.common.enums.Sex;
import com.example.common.enums.identify;
import com.example.common.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
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
    public String toAddManager(){
        return "user/admin-add";
    }

    @RequestMapping("/Manageradd1")
    public String addManager(HttpServletRequest request,Model model){
        String adminName = request.getParameter("adminName");
        String password = request.getParameter("password");
        String sex = request.getParameter("sex");
        User user  =new User();
        user.setSex(Sex.valueOf(sex));
        user.setUsername(adminName);
        user.setPassword(password);
        user.setIdentify(identify.ADMIN);
        userService.addUser(user);
        return "user/admin-list";
    }


    @GetMapping("/User")
    //@RequiresPermissions("/manager:seeUser")
    public String seeUser(Model model){
        List<User>users = userService.getUsers();
        model.addAttribute("users",users);
        model.addAttribute("size",users.size());
        return "user/user-list";
    }

    @RequestMapping("/User/toEdit/{id}")
    public String toEdit(@PathVariable(value = "id")String id,Model model){
        Integer userId = Integer.valueOf(id);
        User user = userService.getUser(userId);
        model.addAttribute("user",user);
        return "user/admin-role-edit";
    }

}
