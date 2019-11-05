package com.example.webrtc.service;

import com.example.webrtc.entity.WorkProcess;
import java.util.List;

public interface WorkService {
    public List<WorkProcess>getProcessByUserId(Integer id,String state);

    public void updateProcess(String state,Integer id);

    public void insertProcess(WorkProcess workProcess);

    public void delProcess(Integer id);

}
