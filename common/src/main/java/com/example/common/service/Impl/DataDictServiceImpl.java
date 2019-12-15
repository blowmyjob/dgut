package com.example.common.service.Impl;

import com.example.common.mapper.DataDictDao;
import com.example.common.service.DataDictServcie;
import com.example.common.vo.DataDict;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataDictServiceImpl implements DataDictServcie {
    private DataDictDao dataDictDao;
    @Override
    public List<DataDict> selectDataDict(String code) {
        return dataDictDao.selectDictByCode(code);
    }

    @Override
    public void insertDict(DataDict dataDict) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void updateDict(DataDict dataDict) {

    }
}
