package com.doyouknow.project.controller;

import com.doyouknow.project.dto.DeptDTO;
import com.doyouknow.project.service.MapService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Controller
public class MapController {

    private final MapService mapService;

    @Autowired
    public MapController(MapService mapService) {
        this.mapService = mapService;
    }

    @GetMapping("/map")
    public String mapPage(HttpServletRequest request, @RequestParam(value = "locDetail", required = false) String locDetail, Model model) {
        System.out.println("확인용 locDetail: " + locDetail);
        HttpSession session = request.getSession();
        System.out.println(session.getAttribute("member"));
        List<DeptDTO> deptInfo;
        if (locDetail != null && !locDetail.isEmpty()) {
            deptInfo = mapService.selectdept(locDetail);
        } else {
            deptInfo = mapService.selectAllDept();
        }

        System.out.println("DeptInfo: " + deptInfo);
        model.addAttribute("deptInfo", deptInfo);

        return "map/map";
    }

    @GetMapping("/mapData")
    @ResponseBody
    public List<DeptDTO> getMapData(@RequestParam(value = "locDetail", required = false) String locDetail) {
        if (locDetail != null && !locDetail.isEmpty()) {
            return mapService.selectdept(locDetail);
        } else {
            return mapService.selectAllDept();
        }
    }

    @GetMapping("/popup")
    public String popupPage(){
        return "/popup/popup";
    }
}
