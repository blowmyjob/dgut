package com.example.common.service.Impl;

import com.example.common.entity.System;
import com.example.common.entity.SystemLog;
import com.example.common.mapper.SystemDao;
import com.example.common.mapper.WorkDao;
import com.example.common.service.SystemService;
import com.example.common.vo.DataDict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SystemServiceImpl implements SystemService {
    @Autowired
    private SystemDao systemDao;

    @Override
    public System selectSystem() {
        return systemDao.selectSystem();
    }

    @Override
    public void updateSystem(System system) {
        systemDao.updateSystem(system);
    }

    @Override
    public void addLog(SystemLog log) {
        systemDao.addLog(log);
    }

    @Override
    public List<SystemLog> getExceptionLogs() {
        return systemDao.getExceptionLogs();
    }

    @Override
    public List<SystemLog> getLogsByType(Map<String, String> map) {
        return systemDao.getLogsByType(map);
    }

    @Override
    public List<DataDict> getDataDict(Map<String, String> map) {
        return systemDao.getDataDict(map);
    }

    @Override
    public List<SystemLog> getLogs() {
        return systemDao.getLogs();
    }

    @Override
    public void addDataDict(DataDict dataDict) {
        systemDao.addDataDict(dataDict);
    }
}
