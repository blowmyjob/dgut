package com.example.common.service;

public interface StaticsService {
    String ChartUserByMonth(Integer userId,Integer month,String state);

    String ChartUserByYear(Integer userId,Integer year,String state);

    String ChartCompanyByMonth(Integer companyId,Integer month,String state);

    String ChartCompanyByYear(Integer companyId,Integer year,String state);

    Integer getCompanyId(Integer userid);
}
