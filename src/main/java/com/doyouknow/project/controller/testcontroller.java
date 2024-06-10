package com.doyouknow.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class testcontroller{

    @GetMapping("/sucess")
    public String showProfile(@SessionAttribute("seq") Integer seq, Model model) {
        model.addAttribute("seq", seq);
        return "login/sucess";
    }
}
