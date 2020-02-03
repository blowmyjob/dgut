package com.example.powermanage.service;


import com.example.powermanage.entity.SysPermission;

import java.util.List;

public interface PermService {

    /**
     * 添加权限
     * @param permission
     */
    public void addPerm(SysPermission permission);

    /**
     * 删除权限
     * @param id
     */
    public void delPerm(Integer id);

    /**
     * 修改权限
     * @param id
     * @param permission
     */
    public void updatePerm(Integer id, SysPermission permission);

    /**
     * 批量删除
     * @param list
     */
    public void delPerms(List<Integer> list);
}
