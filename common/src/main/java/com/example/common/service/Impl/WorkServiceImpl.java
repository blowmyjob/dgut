package com.example.common.service.Impl;

import com.example.common.entity.*;
import com.example.common.mapper.WorkDao;
import com.example.common.entity.WorkProcess;
import com.example.common.mapper.WorkDao;
import com.example.common.service.WorkService;
import com.example.common.vo.Category;
import com.example.common.vo.Relationship;
import com.example.common.vo.WorkDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class WorkServiceImpl implements WorkService {
    @Autowired
    private WorkDao workDao;

    @Override
    public List<WorkProcess> getProcessByUserId(Integer id,String state) {
        return workDao.getProcessByUserId(id,state);
    }

    @Override
    public void updateProcess(String state,Integer roomId,Integer id) {
        workDao.updateProcess(state,roomId,id);
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

    @Override
    public List<Hire> searchJob(Map<String, String> map) {
        return workDao.searchJob(map);
    }

    @Override
    public List<Hire> selectAllJob() {
        return workDao.selectAllJob();
    }

    @Override
    public List<Category> getCatesByCategory(Map<String, String> map) {
        return workDao.getCatesByCategory(map);
    }

    @Override
    public List<Category> getCatesByLocation(Map<String, String> map) {
        return workDao.getCatesByLocation(map);
    }

    @Override
    public Integer getJobLastWeek() {
        return workDao.getJobLastWeek();
    }

    @Override
    public void createRelationship(Integer userid, Integer companyid, String position) {
        Relationship relationship = new Relationship();
        relationship.setCompanyid(companyid);
        relationship.setPosition(position);
        relationship.setUserid(userid);
        workDao.createRelationship(relationship);
    }

    @Override
    public void createCompany(Company company) {
        workDao.createCompany(company);
    }

    @Override
    public Company selectCompany(Integer id) {
        return workDao.selectCompanyById(id);
    }

    @Override
    public List<Employee> getEmployee(Map<String, String> map) {
        return workDao.selectEmploy(map);
    }

    @Override
    public void changeHire(String state,Integer id) {
        workDao.downHire(state,id);
    }

    @Override
    public List<Hire> selectHire(Map<String, String> map) {
        return workDao.selectHire(map);
    }
}
