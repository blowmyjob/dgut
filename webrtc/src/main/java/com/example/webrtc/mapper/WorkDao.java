package com.example.webrtc.mapper;

import com.example.webrtc.entity.WorkProcess;
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

    @Insert("insert into Workprocess (userid,state,jobid) values(#{userid),'待查看',#{jobid}")
    public void insertProcess(WorkProcess workProcess);

    @Delete("delete from Workprocess where id = #{id}")
    public void delProcess(Integer id);

    @Select("select * from Workprocess where id = #{id} and state = #{state}")
    public List<WorkProcess>getProcessByIdAndState(Integer id,String state);


}
