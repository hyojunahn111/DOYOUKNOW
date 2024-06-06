package com.doyouknow.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class adminPageController {
    @GetMapping("/adminPage")
    public String adminPage(){return "adminPage/adminPage";}
}
