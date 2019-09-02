package com.example.common.entity;

import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public class SysRole {
    private Integer id; // 编号
    private String role;                       // 角色标识程序中判断使用,如"admin",这个是唯一的:
    private String description;                // 角色描述,UI界面显示使用
    private String username;                      //用户名
    private List<SysPermission> syspermissions;   //若干的权限
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<SysPermission> getSyspermissions() {
        return syspermissions;
    }

    public void setSyspermissions(List<SysPermission> syspermissions) {
        this.syspermissions = syspermissions;
    }
}
