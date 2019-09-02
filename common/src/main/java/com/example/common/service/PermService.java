package com.example.common.service;

import com.example.common.entity.SysPermission;

import java.util.List;

public interface PermService {
    /**
     * 查看权限
     * @return
     */
    public List<SysPermission>seePerm();

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
    public void updatePerm(Integer id,SysPermission permission);
}
