package com.example.common.mapper;

import com.example.common.entity.Hire;
import com.example.common.entity.WorkProcess;
import com.example.common.vo.Category;
import com.example.common.vo.WorkDetail;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface WorkDao {
    public List<WorkProcess>getProcessByUserId(Integer id,String state);

    public void updateProcess(String state,Integer id);

    public void insertProcess(WorkProcess workProcess);

    public void delProcess(Integer id);

    public List<WorkProcess>getProcessByIdAndState(Integer id,String state);

    public List<WorkDetail>getWorkDetails(Integer userid,String state);

    public List<WorkDetail>getProcessByCompanyId(Integer companyid,String state);

    public Integer findCompanyIdByUserId(Integer userid);

    public void updateState(String state,Integer id);

    public void insertJob(Hire hire);

    public List<Hire> selectAllJob();

    public List<Hire> searchJob(Map<String,String>map);

    public List<Category>getCatesByCategory(Map<String,String>map);

    public List<Category>getCatesByLocation(Map<String,String>map);
}
