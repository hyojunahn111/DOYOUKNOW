package com.doyouknow.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
public class HeaderController {

    @GetMapping("/header")
    public String header(@SessionAttribute("seq") int seq, Model model) {
        System.out.println("넘어오는 세션 값 : " + seq);

        model.addAttribute("seq", seq);

        return "fragment/header/header";
    }
}
