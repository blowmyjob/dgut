package com.example.common.service.Impl;

import com.example.common.mapper.DataDictDao;
import com.example.common.service.DataDictServcie;
import com.example.common.vo.DataDict;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataDictServiceImpl implements DataDictServcie {
    @Autowired
    private DataDictDao dataDictDao;
    @Override
    public List<DataDict> selectDataDict(String code) {
        return dataDictDao.selectDictByCode(code);
    }

    @Override
    public void insertDict(DataDict dataDict) {
        dataDictDao.insert(dataDict);
    }

    @Override
    public void deleteById(Integer id) {
        dataDictDao.delete(id);
    }

    @Override
    public void updateDict(DataDict dataDict) {
        dataDictDao.update(dataDict);
    }

    @Override
    public List<DataDict> selectDataDicts() {
        List<DataDict>dataDicts= dataDictDao.selectDataDicts();
        return dataDicts;
    }
}
