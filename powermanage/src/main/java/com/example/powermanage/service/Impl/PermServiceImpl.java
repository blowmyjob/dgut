package com.example.powermanage.service.Impl;


import com.example.powermanage.entity.SysPermission;
import com.example.powermanage.mapper.PermDao;
import com.example.powermanage.service.PermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PermServiceImpl implements PermService {
    @Autowired
    private PermDao permDao;

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
}
