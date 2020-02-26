package com.example.common.service;

import com.example.common.entity.System;
import com.example.common.entity.SystemLog;
import com.example.common.vo.DataDict;

import java.util.List;
import java.util.Map;

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

    /**
     * 按照类型查找日志
     * @param map
     * @return
     */
    List<SystemLog> getLogsByType(Map<String,String> map);

    /**
     * 查找数据字典
     * @param map
     * @return
     */
    List<DataDict> getDataDict(Map<String,String>map);
}
