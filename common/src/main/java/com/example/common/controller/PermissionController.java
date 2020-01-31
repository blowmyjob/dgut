package com.example.common.controller;

import com.example.common.entity.SysPermission;
import com.example.common.entity.SysRole;
import com.example.common.entity.User;
import com.example.common.service.PermService;
import com.example.common.service.RoleService;
import com.example.common.service.UserService;
import com.example.common.tools.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
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

    @RequiresPermissions("userInfo:role")
    @GetMapping("/user/toEditRole")
    public String toEditRole(@PathVariable("id")Integer id, Model model){
        model.addAttribute("id",id);
        return "user/admin-role-edit";
    }


    @RequestMapping("/user/editRole/{id}")
    public String editRole(@PathVariable("id")Integer id, Model model){
        SysRole role = roleService.getRoleById(id);
        return "user/admin-role-edit";
    }

    @DeleteMapping("/Role/{id}")
    @ResponseBody
    public String delRole(@PathVariable("id")String id){
        Integer userId = Integer.valueOf(id);
        try{
            userService.delUser(userId);
            return Result.SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR;
        }
    }

    @GetMapping("/Role/add")
    public String toAdd(){
        return "user/admin-role-add";
    }

    @PostMapping("/Role/add1")
    @ResponseBody
    public String addRole(HttpServletRequest request){
        String roleName = request.getParameter("roleName");
        return Result.SUCCESS;
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
        model.addAttribute("count",users.size());
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
        try{
            Integer userId = Integer.valueOf(id);
            userService.delUser(userId);
            return Result.SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR;
        }
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
        try {
            userService.checkUser(Integer.valueOf(id));
            return Result.SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR;
        }
    }


    /**********管理员模块************/
    @DeleteMapping("/admin/{id}")
    @ResponseBody
    public String adminDelete(@PathVariable("id")String id){
        Integer userId = Integer.valueOf(id);
        try {
            userService.delUser(userId);
            return Result.SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR;
        }
    }

    @RequestMapping("/admin/start/{id}")
    @ResponseBody
    public String adminStart(@PathVariable("id")String id){
        Integer userId = Integer.valueOf(id);
        try{
            userService.checkUser(userId);
            return Result.SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR;
        }
    }

    @RequestMapping("/admin/stop/{id}")
    @ResponseBody
    public String adminStop(@PathVariable("id")String id){
        Integer userId = Integer.valueOf(id);
        try{
            userService.stopUser(userId);
            return Result.SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR;
        }
    }
}
