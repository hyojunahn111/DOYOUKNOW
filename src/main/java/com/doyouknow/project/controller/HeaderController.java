package com.doyouknow.project.controller;

import com.doyouknow.project.dto.DeptDTO;
import com.doyouknow.project.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;

@Controller
public class HeaderController {

    private final MapService mapService;

    @Autowired
    public HeaderController(MapService mapService) {
        this.mapService = mapService;
    }


    @GetMapping("/header")
    public String header(@SessionAttribute("seq") int seq, Model model) {
        System.out.println("넘어오는 세션 값 : " + seq);

        List<DeptDTO> deptInfo = mapService.selectAllDept();

        for (DeptDTO deptDTO : deptInfo) {
            System.out.println(deptDTO.getName());
        }

        System.out.println(deptInfo);


        model.addAttribute("seq", seq);
        model.addAttribute("deptInfo", deptInfo);

        return "fragment/header/header";
    }
}
