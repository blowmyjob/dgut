package com.example.common.mapper;

import com.example.common.entity.Statics;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StaticsDao {
    List<Statics>getUserOneMonthById(Integer userId,Integer month,String state);

    List<Statics>getUserOneYearById(Integer userId,Integer year,String state);

    List<Statics>getCompanyOneMonthById(Integer companyid,Integer month,String state);

    List<Statics>getCompanyOneYearById(Integer companyid,Integer year,String state);

    Integer getCompanyIdByUserId(Integer userId);
}
