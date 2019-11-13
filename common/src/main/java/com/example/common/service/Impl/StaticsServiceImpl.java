package com.example.common.service.Impl;

import com.alibaba.fastjson.JSON;
import com.example.common.entity.Statics;
import com.example.common.mapper.StaticsDao;
import com.example.common.service.StaticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaticsServiceImpl implements StaticsService {
    @Autowired
    private StaticsDao staticsDao;


    @Override
    public String ChartUserByMonth(Integer userId, Integer month, String state) {
        List<Statics>statics = staticsDao.getUserOneMonthById(userId,month,state);
        return JSON.toJSONString(statics);
    }

    @Override
    public String ChartUserByYear(Integer userId, Integer year, String state) {
        List<Statics>statics = staticsDao.getUserOneYearById(userId,year,state);
        return JSON.toJSONString(statics);
    }

    @Override
    public String ChartCompanyByMonth(Integer companyId, Integer month) {
        List<Statics>statics = staticsDao.getCompanyOneMonthById(companyId,month);
        return JSON.toJSONString(statics);
    }

    @Override
    public String ChartCompanyByYear(Integer companyId, Integer year) {
        List<Statics>statics = staticsDao.getCompanyOneYearById(companyId,year);
        return JSON.toJSONString(statics);
    }

}
