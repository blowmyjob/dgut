package com.example.common.service.Impl;

import com.example.common.entity.SysRole;
import com.example.common.mapper.PermDao;
import com.example.common.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private PermDao permDao;

    @Override
    public List<SysRole> seeRole() {
        return permDao.getRole();
    }

    @Override
    public void addRole(SysRole role) {
        permDao.addRole(role);
    }

    @Override
    public void delRole(Integer id) {
        permDao.deleteRole(id);
    }

    @Override
    public void updateRole(Integer id, SysRole role) {
        permDao.updateRole(role,id);
    }
}
