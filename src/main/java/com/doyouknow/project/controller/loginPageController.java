package com.doyouknow.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class loginPageController {

    @GetMapping("/login")
    public String login(){return "login/login";}

    @GetMapping("/signup")
    public String signup(){return "login/signup";}

    @GetMapping("/searchid")
    public String searchid(){return "login/searchid";}

    @GetMapping("/searchpwd")
    public String searchpwd(){return "login/searchpwd";}


}



