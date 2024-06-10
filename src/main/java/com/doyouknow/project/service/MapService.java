package com.doyouknow.project.service;

import com.doyouknow.project.dao.MapDAO;
import com.doyouknow.project.dto.DeptDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapService {

    private final MapDAO mapDAO;

    @Autowired
    public MapService(MapDAO mapDAO) {
        this.mapDAO = mapDAO;
    }

    public List<DeptDTO> selectdept(String locDetail) {
        return mapDAO.selectByLocDetail(locDetail);
    }

}
