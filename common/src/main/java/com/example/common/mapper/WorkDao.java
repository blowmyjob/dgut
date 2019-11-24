package com.example.common.mapper;

import com.example.common.entity.WorkProcess;
import com.example.common.vo.WorkDetail;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface WorkDao {
    @Select("select * from Workprocess where userid = #{id} and state = #{state} and visable = 'true'")
    public List<WorkProcess>getProcessByUserId(Integer id,String state);

    @Update("update Workprocess set state = #{state} where id = #{id}")
    public void updateProcess(String state,Integer id);

    @Insert("insert into Workprocess (userid,state,jobid,resumeid) values(#{userid},'待查看',#{jobid},#{resumeid})")
    public void insertProcess(WorkProcess workProcess);

    @Delete("delete from Workprocess where id = #{id}")
    public void delProcess(Integer id);

    @Select("select * from Workprocess where id = #{id} and state = #{state}")
    public List<WorkProcess>getProcessByIdAndState(Integer id,String state);

    @Select("select DISTINCT Workprocess.id as id,userid,jobid,state,username as name,Hire.description,companyname,Hire.description as jobdescription,updatetime from Company,Workprocess ,User,Hire where Company.id=Workprocess.Companyid and Workprocess.userid=#{userid} and Workprocess.state=#{state} and Hire.companyid=Workprocess.companyid and User.id = Workprocess.userid")
    public List<WorkDetail>getWorkDetails(Integer userid,String state);

    @Select("select Workprocess.id as id,Workprocess.userid,state,jobid,companyid,updatetime from Workprocess,User where User.id = Workprocess.userid and Workprocess.companyid={companyid} and state=#{state}")
    public List<WorkDetail>getProcessByCompanyId(Integer companyid,String state);

    @Select("select companyid from Employee_Relationship where userid = #{userid}")
    public Integer findCompanyIdByUserId(Integer userid);

    @Update("update Workprocess set visable = #{state} where id = #{id}")
    public void updateState(String state,Integer id);
}
