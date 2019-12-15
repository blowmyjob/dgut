package com.example.common.service;

import com.example.common.entity.Hire;
import com.example.common.entity.WorkProcess;
import com.example.common.vo.WorkDetail;

import java.util.List;

public interface WorkService {
    public List<WorkProcess>getProcessByUserId(Integer id,String state);

    public void updateProcess(String state,Integer id);

    public void insertProcess(WorkProcess workProcess);

    public void delProcessByUser(Integer id);

    public List<WorkDetail> getWorkDetailsByUserId(Integer userid,String state);

    public List<WorkDetail> getWorkDetailsByCompanyId(Integer companyid,String state);

    public Integer findCompanyIdByUserId(Integer userid);

    public void delProcessByHr(Integer id);

    public void insertHire(Hire hire);

    public List<Hire> selectAllJob();
}
