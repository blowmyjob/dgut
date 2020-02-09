package com.example.common.service;

import com.example.common.vo.DataDict;
import java.util.List;

public interface DataDictServcie {
    public List<DataDict>selectDataDict(String code);

    public void insertDict(DataDict dataDict);

    public void deleteById(Integer id);

    public void updateDict(DataDict dataDict);

    public List<DataDict>selectDataDicts();
}
