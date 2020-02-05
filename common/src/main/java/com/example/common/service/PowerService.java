package com.example.common.service;

import com.example.common.entity.SysRole;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Repository
@FeignClient(value = "powermanage")
public interface PowerService {

    @PostMapping("/Role/edit")
    public String EditRole(HttpServletRequest request);

    @DeleteMapping("/Role/{id}")
    public String delRole(@PathVariable("id")Integer id);


    @PostMapping("/Role/add")
    public String addRole(@RequestBody SysRole role, @RequestParam("permission") List<Integer> permission);

    @PostMapping("/user/delRoles")
    public String delRoles(String checkList);


    @PostMapping("/user/addPerm")
    public String addPerm(HttpServletRequest request);

    @PostMapping("/user/delPerm/{id}")
    public String delPerm(@PathVariable("id")Integer id);

    @PostMapping("/user/delPerms")
    public String delPerms(String checkList);
}
