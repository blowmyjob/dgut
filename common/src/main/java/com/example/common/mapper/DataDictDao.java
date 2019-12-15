package com.example.common.mapper;

import com.example.common.vo.DataDict;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DataDictDao {
    public List<DataDict>selectDictByCode(String code);
}
