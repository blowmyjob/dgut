package com.example.common.service;

import com.example.common.entity.Hire;
import com.example.common.entity.WorkProcess;
import com.example.common.vo.Category;
import com.example.common.vo.WorkDetail;

import java.util.List;
import java.util.Map;

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

    public List<Hire> searchJob(Map<String,String> map);

    public List<Category>getCatesByCategory(Map<String,String>map);

    public List<Category>getCatesByLocation(Map<String,String>map);
}
