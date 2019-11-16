package com.example.common.mapper;

import com.example.common.entity.Statics;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StaticsDao {
    @Select("select month(updatetime) as month,Workprocess.id as id,state,workname,count(*) as count from Workprocess,Hire where Workprocess.userid = #{userId} and month(updatetime)=#{month} and Workprocess.state=#{state} and Workprocess.jobid = Hire.id GROUP BY month(updatetime)")
    List<Statics>getUserOneMonthById(Integer userId,Integer month,String state);

    @Select("select year(updatetime) as year,Workprocess.id as id,state,workname,count(*) as count from Workprocess,Hire where userid = #{userId} and year(updatetime)=#{year} and state=#{state} and Workprocess.jobid = Hire.id group by year(updatetime)")
    List<Statics>getUserOneYearById(Integer userId,Integer year,String state);

    @Select("select month(updatetime) as month,Workprocess.id as id,state,workname,count(*) as count from Workprocess,Hire where Workprocess.companyid = #{companyid} and month(updatetime)=#{month} and Workprocess.jobid = Hire.id GROUP BY month(updatetime)")
    List<Statics>getCompanyOneMonthById(Integer companyid,Integer month);

    @Select("select count(*),year(updatetime) as year,Workprocess.id as id,state,workname,count(*) as count from Workprocess,Hire where Workprocess.companyid = #{companyid} and year(updatetime)=#{year} and Workprocess.jobid = Hire.id GROUP BY year(updatetime)")
    List<Statics>getCompanyOneYearById(Integer companyid,Integer year);
}
