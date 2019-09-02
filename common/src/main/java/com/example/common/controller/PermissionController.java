package com.example.common.controller;

import com.example.common.entity.SysPermission;
import com.example.common.entity.SysRole;
import com.example.common.entity.User;
import com.example.common.service.PermService;
import com.example.common.service.RoleService;
import com.example.common.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PermissionController {
    @Autowired
    private UserService userService;

    @Autowired
    private PermService permService;

    @Autowired
    private RoleService roleService;

    /*************角色模块*************/

    /**
     * 获取所有的角色
     * @param model
     * @return
     */
    @GetMapping("/Role")
    @RequiresPermissions("userInfo:role")
    public String gets(Model model){
        List<SysRole>roles  = roleService.seeRole();
        model.addAttribute("roles",roles);
        model.addAttribute("count",roles.size());
        return "user/admin-role";
    }

    /**
     * 修改角色
     * @return
     */
    @RequiresPermissions("userInfo:role")
    @PostMapping("/user/addRole")
    public String addRole(){
        return "";
    }

    /*************权限模块**************/
    /**
     * 查看权限
     * @return
     */
    @RequiresPermissions("userInfo:perm")
    @GetMapping("/Perm")
    public String seePerm(Model model){
        List<SysPermission>permissions  = permService.seePerm();
        model.addAttribute("permissions",permissions);
        model.addAttribute("count",permissions.size());
        return "user/admin-permission";
    }

    /**
     * 添加权限
     * @param id
     * @param permId
     * @return
     */
    @RequiresPermissions("userPerm:add")
    @PostMapping("/user/addPerm/{permId}/{id}")
    public String addPerm(String permId,String id){
        return "1";
    }

    /**
     * 删除权限
     * @param permId
     * @param id
     * @return
     */
    @RequiresPermissions("userPerm:del")
    @PostMapping("/user/delPerm/{permId}/id")
    public String delPerm(String permId,String id){
        return "1";
    }



    /**********用户模块************/
    /**
     * 获取所有的用户
     * @param model
     * @return
     */
    @GetMapping("/users")
    @RequiresPermissions("userPerm:list")
    public String getUsers(Model model){
        List<User> users = userService.getUsers();
        model.addAttribute("users",users);
        return "user/user-list";
    }

    /**
     * 查找某一个用户
     * @param id
     * @return
     */
    @GetMapping("/user/{id}")
    public String getUser(@PathVariable("id")String id){
        return "";
    }

    /**
     * 用户删除;
     * @return
     */
    @RequiresPermissions("userInfo:del")//权限管理;
    @DeleteMapping("/user/{id}")
    @ResponseBody
    public String deleteUser(@PathVariable("id")String id){
        userService.delUser(Integer.valueOf(id));
        return "1";
    }

    /**
     * 用户审核
     * @param id
     * @return
     */
    @RequiresPermissions("userInfo:check")
    @PostMapping("/user/check/{id}")
    @ResponseBody
    public String checkUser(@PathVariable("id")String id){
        userService.checkUser(Integer.valueOf(id));
        return "1";
    }
}
