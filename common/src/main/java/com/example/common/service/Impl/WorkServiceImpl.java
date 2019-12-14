package com.example.common.service.Impl;

import com.example.common.entity.Hire;
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
    public void delProcessByUser(Integer id) {
        workDao.updateState("false",id);
    }

    @Override
    public Integer findCompanyIdByUserId(Integer userid) {
        return workDao.findCompanyIdByUserId(userid);
    }

    @Override
    public List<WorkDetail> getWorkDetailsByCompanyId(Integer companyid, String state) {
        return workDao.getProcessByCompanyId(companyid,state);
    }

    @Override
    public List<WorkDetail> getWorkDetailsByUserId(Integer userid, String state) {
        return workDao.getWorkDetails(userid,state);
    }

    @Override
    public void delProcessByHr(Integer id) {
        workDao.delProcess(id);
    }

    @Override
    public void insertHire(Hire hire) {
        workDao.insertJob(hire);
    }
}