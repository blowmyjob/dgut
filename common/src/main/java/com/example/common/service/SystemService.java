package com.example.common.service;

import com.example.common.entity.System;
import com.example.common.entity.SystemLog;

import java.util.List;

public interface SystemService {
    /**
     * 查找系统配置
     * @return
     */
    System selectSystem();

    /**
     * 修改系统配置
     * @param system
     */
    void updateSystem(System system);

    /**
     * 获得系统日志
     * @return
     */
    List<SystemLog> getLogs();


    /**
     * 添加系统日志
     * @param log
     */
    void addLog(SystemLog log);

    /**
     * 获取异常日志
     * @return
     */
    List<SystemLog> getExceptionLogs();
}
