package com.example.common.mapper;

import com.example.common.entity.System;
import com.example.common.entity.SystemLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SystemDao {
    @Select("select * from System")
    public System selectSystem();

    @Select("update System set websiteTitle=#{websiteTitle},websiteDescription=#{websiteDescription},websiteUploadfile=#{websiteUploadfile}")
    public void updateSystem(System system);

    @Select("select * from SystemLog")
    public List<SystemLog> getLogs();

    @Insert("insert into SystemLog (userName,content,ipAddress,loginTime) values (#{userName},#{content},#{ipAddress},#{loginTime})")
    public void addLog(SystemLog log);
}
