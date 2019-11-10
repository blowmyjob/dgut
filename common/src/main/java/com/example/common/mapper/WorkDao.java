package com.example.common.mapper;

import com.example.common.entity.WorkProcess;
import com.example.common.vo.WorkDetail;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface WorkDao {
    @Select("select * from Workprocess where companyid = #{id} and state = #{state}")
    public List<WorkProcess>getProcessByUserId(Integer id,String state);

    @Update("update Workprocess set state = #{state} where id = #{id}")
    public void updateProcess(String state,Integer id);

    @Insert("insert into Workprocess (userid,state,jobid,resumeid) values(#{userid},'待查看',#{jobid},#{resumeid})")
    public void insertProcess(WorkProcess workProcess);

    @Delete("delete from Workprocess where id = #{id}")
    public void delProcess(Integer id);

    @Select("select * from Workprocess where id = #{id} and state = #{state}")
    public List<WorkProcess>getProcessByIdAndState(Integer id,String state);

    @Select("select Workprocess.userid as id,userid,jobid,state,username as name,Hire.description,companyname,Hire.description as jobdescription from Company,Workprocess,User,Hire where Company.id=Workprocess.Companyid and Workprocess.userid=User.id and Workprocess.userid=#{userid}")
    public List<WorkDetail>getWorkDetails(Integer userid,String state);


}
