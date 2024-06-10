package com.doyouknow.project.controller;

import com.doyouknow.project.dto.DeptDTO;
import com.doyouknow.project.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MapController {

    private final MapService mapService;

    @Autowired
    public MapController(MapService mapService) {
        this.mapService = mapService;
    }

    @GetMapping({"/","map"})
    public String mapPage(String locDetail) {

//        locDetail = "건물5";
//
//        List<DeptDTO> deptinfo = mapService.selectdept(locDetail);
//        System.out.println(deptinfo);




        return "map/map";
    }

}
