package com.example.common.service.Impl;

import com.example.common.entity.SysPermission;
import com.example.common.mapper.PermDao;
import com.example.common.service.PermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PermServiceImpl implements PermService {
    @Autowired
    private PermDao permDao;

    @Override
    public List<SysPermission> seePerm() {
        return permDao.getPerm();
    }

    @Override
    public void addPerm(SysPermission permission) {
        permDao.addPerm(permission);
    }

    @Override
    @Transactional
    public void delPerm(Integer id) {
        permDao.delPerm(id);
        //permDao.delRolePermByPerm(id);
    }

    @Override
    public void updatePerm(Integer id, SysPermission permission) {
        permDao.updatePerm(permission,id);
    }

    @Override
    public void delPerms(List<Integer> list) {
        permDao.delPerms(list);
    }

    @Override
    public List<SysPermission> getPermByUserId(Integer id) {
        return permDao.getPermByUserid(id);
    }
}
