package com.doyouknow.project.service;

import com.doyouknow.project.dto.DeptDTO;
import com.doyouknow.project.mapper.MapMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapService {

    private final MapMapper mapper;

    @Autowired
    public MapService(MapMapper mapper) {
        this.mapper = mapper;
    }

    public List<DeptDTO> selectAllDept(){
        return mapper.selectAll();
    }

}
