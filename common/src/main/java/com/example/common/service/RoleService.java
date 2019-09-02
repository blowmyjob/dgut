package com.example.common.service;

import com.example.common.entity.SysRole;

import java.util.List;

public interface RoleService {
    /**
     * 查看角色
     * @return
     */
    public List<SysRole>seeRole();

    /**
     * 添加角色
     * @param role
     */
    public void addRole(SysRole role);

    /**
     * 删除角色
     * @param id
     */
    public void delRole(Integer id);

    /**
     * 修改角色
     * @param id
     * @param role
     */
    public void updateRole(Integer id,SysRole role);
}
