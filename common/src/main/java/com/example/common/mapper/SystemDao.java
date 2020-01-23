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
    public System selectSystem();

    public void updateSystem(System system);

    public List<SystemLog> getLogs();

    public void addLog(SystemLog log);

    public List<SystemLog> getExceptionLogs();
}
