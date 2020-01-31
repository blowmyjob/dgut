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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
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
    @GetMapping("/user/toEditRole/{id}")
    public String toEditRole(@PathVariable("id")Integer id, Model model){
        List<SysPermission>sysPermissions = permService.seePerm();
        model.addAttribute("id",id);
        model.addAttribute("sysPermissions",sysPermissions);
        return "user/admin-role-edit";
    }

    @PostMapping("/Role/edit")
    @ResponseBody
    public String EditRole(HttpServletRequest request){
        try{
            Integer id = Integer.valueOf(request.getParameter("id"));
            String roleName = request.getParameter("roleName");
            String description = request.getParameter("description");
            String []permissions = request.getParameterValues("permission");
            List<Integer>permission = new ArrayList<>();
            for(String temp : permissions){
                permission.add(Integer.valueOf(temp));
            }
            SysRole role = new SysRole();
            role.setRole(roleName); role.setDescription(description);
            roleService.updateRole(id,role);
            roleService.updateRolePerm(permission,id);
            return Result.SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR;
        }
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
    public String toAdd(Model model){
        List<SysPermission>sysPermissions = permService.seePerm();
        model.addAttribute("sysPermissions",sysPermissions);
        return "user/admin-role-add";
    }

    @PostMapping("/Role/add")
    @ResponseBody
    @Transactional
    public String addRole(HttpServletRequest request){
        try{
            String roleName = request.getParameter("roleName");
            String description = request.getParameter("description");
            String []permissions = request.getParameterValues("permission");
            List<Integer>permission = new ArrayList<>();
            for(String temp : permissions){
                permission.add(Integer.valueOf(temp));
            }
            SysRole role = new SysRole();
            role.setRole(roleName); role.setDescription(description);
            roleService.addRole(role,permission);
            return Result.SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR;
        }
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
     * @return
     */
    @GetMapping("/user/addPerm")
    public String toAddPerm(){
        return "user/admin-permission-add";
    }

    @PostMapping("/user/addPerm")
    @ResponseBody
    public String addPerm(HttpServletRequest request){
        try{
            String permName = request.getParameter("permName");
            String url = request.getParameter("url");
            String permission = request.getParameter("permission");
            SysPermission sysPermission = new SysPermission();
            sysPermission.setName(permName); sysPermission.setPermission(permission); sysPermission.setUrl(url);
            permService.addPerm(sysPermission);
            return Result.SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR;
        }
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
