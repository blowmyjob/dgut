package com.example.common.controller;

import com.example.common.entity.SysRole;
import com.example.common.entity.User;
import com.example.common.enums.Sex;
import com.example.common.enums.identify;
import com.example.common.service.UserService;
import com.example.common.tools.Result;
import org.apache.commons.collections.map.HashedMap;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @PostMapping("/Manageradd")
    @ResponseBody
    public String addManager(HttpServletRequest request,Model model){
        try{
            String adminName = request.getParameter("adminName");
            String password = request.getParameter("password");
            String sex = request.getParameter("sex");
            User user  =new User();
            user.setSex(Sex.valueOf(sex));
            user.setUsername(adminName);
            user.setPassword(password);
            user.setIdentify(identify.ADMIN);
            userService.addUser(user);
            return Result.SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR;
        }
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

    @GetMapping("/User/selectDel")
    public String selectUserByDelete(Model model){
        Map<String,String>map = new HashMap();
        map.put("isdel","true");
        List<User>users = userService.selectUserByType(map);
        model.addAttribute("users",users);
        model.addAttribute("size",users.size());
        return "user/user-list";
    }


    @PostMapping("/User/userDel")
    @ResponseBody
    public String userdel(String checkList){
        try{
            System.out.println("==>"+checkList);
            String[] strs = checkList.split(",");
            List<Integer> ids = new ArrayList<>();
            for(String str:strs){
                ids.add(Integer.parseInt(str));
            }
            userService.delUsers(ids);
            return Result.SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR;
        }
    }
}
