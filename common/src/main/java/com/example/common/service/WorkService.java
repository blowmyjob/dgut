package com.example.common.service;

import com.example.common.entity.WorkProcess;
import com.example.common.vo.WorkDetail;

import java.util.List;

public interface WorkService {
    public List<WorkProcess>getProcessByUserId(Integer id,String state);

    public void updateProcess(String state,Integer id);

    public void insertProcess(WorkProcess workProcess);

    public void delProcess(Integer id);

    public List<WorkDetail> getWorkDetails(Integer userid,String state);
}
