package com.doyouknow.project.dao;

import com.doyouknow.project.dto.DeptDTO;
import com.doyouknow.project.mapper.MapMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("mapDAO")
public class MapDAO {

    @Autowired
    private MapMapper mapper;

    public List<DeptDTO> selectByLocDetail(String locDetail){
        return mapper.selectByLocDetail(locDetail);
    }



}
