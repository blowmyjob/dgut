package com.example.powermanage.service;



import com.example.powermanage.entity.SysRole;

import java.util.List;

public interface RoleService {

    /**
     * 添加角色
     * @param role
     */
    public void addRole(SysRole role, List<Integer> list);

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
    public void updateRole(Integer id, SysRole role);


    /**
     * 修改角色的权限
     * @param permission
     * @param id
     */
    public void updateRolePerm(List<Integer> permission, Integer id);

    /**
     * 批量删除
     * @param list
     */
    public void delRoles(List<Integer> list);
}
