package com.example.webrtc.service.Impl;

import com.example.webrtc.entity.WorkProcess;
import com.example.webrtc.mapper.WorkDao;
import com.example.webrtc.service.WorkService;
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
}
