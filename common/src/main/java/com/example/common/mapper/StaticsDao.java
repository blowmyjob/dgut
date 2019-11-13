package com.example.common.mapper;

import com.example.common.entity.Statics;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StaticsDao {
    @Select("select Workprocess.id as id,state,workname from Workprocess,Hire where userid = #{userId} and month(updatetime)=#{month} and state=#{state}")
    List<Statics>getUserOneMonthById(Integer userId,Integer month,String state);

    @Select("select Workprocess.id as id,state,workname from Workprocess,Hire where userid = #{userId} and year(updatetime)=#{year} and state=#{state}")
    List<Statics>getUserOneYearById(Integer userId,Integer year,String state);

    @Select("select Workprocess.id as id,state,workname from Workprocess,Hire where userid = #{userid} and month(updatetime)=#{month} and state=#{state}")
    List<Statics>getCompanyOneMonthById(Integer companyId,Integer month);

    @Select("select Workprocess.id as id,state,workname from Workprocess,Hire where userid = #{userid} and year(updatetime)=#{year} and state=#{state}")
    List<Statics>getCompanyOneYearById(Integer companyId,Integer year);
}
