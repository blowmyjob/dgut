package com.example.common.service.Impl;

import com.example.common.entity.WorkProcess;
import com.example.common.mapper.WorkDao;
import com.example.common.entity.WorkProcess;
import com.example.common.mapper.WorkDao;
import com.example.common.service.WorkService;
import com.example.common.vo.WorkDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkServiceImpl implements WorkService {
    @Autowired
    private WorkDao workDao;

    @Override
    public List<WorkProcess> getProcessByUserId(Integer id,String state) {
        return workDao.getProcessByUserId(id,state);
    }

    @Override
    public void updateProcess(String state,Integer id) {
        workDao.updateProcess(state,id);
    }


    @Override
    public void insertProcess(WorkProcess workProcess) {
        workDao.insertProcess(workProcess);
    }

    @Override
    public void delProcess(Integer id) {
        workDao.delProcess(id);
    }

    @Override
    public List<WorkDetail> getWorkDetails(Integer userid, String state) {
        return workDao.getWorkDetails(userid,state);
    }
}
