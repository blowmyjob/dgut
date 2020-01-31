package com.example.common.service.Impl;

import com.example.common.entity.SysRole;
import com.example.common.mapper.PermDao;
import com.example.common.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public void addRole(SysRole role,List<Integer>list) {
        permDao.addRole(role);
        if(role.getId()!=null){
            permDao.addRolePerm(list,role.getId());
        }
    }

    @Override
    public void delRole(Integer id) {
        permDao.deleteRole(id);
    }

    @Override
    public void updateRole(Integer id, SysRole role) {
        permDao.updateRole(role,id);
    }

    @Override
    public SysRole getRoleById(Integer id) {
        return permDao.getRoleById(id);
    }

    @Override
    @Transactional
    public void updateRolePerm(List<Integer> permission, Integer id) {
        permDao.delRolePerm(id);
        permDao.addRolePerm(permission,id);
    }
}
