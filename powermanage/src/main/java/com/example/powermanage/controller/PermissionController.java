package com.example.powermanage.controller;

import com.example.powermanage.entity.Result;
import com.example.powermanage.entity.SysPermission;
import com.example.powermanage.entity.SysRole;
import com.example.powermanage.service.PermService;
import com.example.powermanage.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PermissionController {

    @Autowired
    private PermService permService;

    @Autowired
    private RoleService roleService;


    /*************角色模块*************/

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
    public String delRole(@PathVariable("id")Integer id){
        try{
            roleService.delRole(id);
            return Result.SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR;
        }
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

    @PostMapping("/user/delRoles")
    @ResponseBody
    public String delRoles(String checkList){
        try{
            String[] strs = checkList.split(",");
            List<Integer> ids = new ArrayList<>();
            for(String str:strs){
                ids.add(Integer.parseInt(str));
            }
            roleService.delRoles(ids);
            return Result.SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR;
        }
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
     * @param id
     * @return
     */
    @PostMapping("/user/delPerm/{id}")
    @ResponseBody
    public String delPerm(@PathVariable("id")Integer id){
        try{
            permService.delPerm(id);
            return Result.SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR;
        }
    }

    @PostMapping("/user/delPerms")
    @ResponseBody
    public String delPerms(String checkList){
        try{
            String[] strs = checkList.split(",");
            List<Integer> ids = new ArrayList<>();
            for(String str:strs){
                ids.add(Integer.parseInt(str));
            }
            permService.delPerms(ids);
            return Result.SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR;
        }
    }
}
