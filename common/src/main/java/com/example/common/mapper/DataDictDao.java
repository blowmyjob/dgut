package com.example.common.mapper;

import com.example.common.vo.DataDict;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.List;

@Mapper
@Repository
public interface DataDictDao {
    public List<DataDict>selectDictByCode(String code);

    public List<DataDict>selectDataDicts();

    public void insert(DataDict dataDict);

    public void delete(Integer id);

    public void update(DataDict dataDict);
}
