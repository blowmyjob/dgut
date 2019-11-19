package com.example.common.service;

public interface StaticsService {
    String ChartUserByMonth(Integer userId,Integer month,String state);

    String ChartUserByYear(Integer userId,Integer year,String state);

    String ChartCompanyByMonth(Integer companyId,Integer month);

    String ChartCompanyByYear(Integer companyId,Integer year);

    Integer getCompanyId(Integer userid);
}
