package com.doyouknow.project.controller;

import com.doyouknow.project.dto.MemberDTO;
import com.doyouknow.project.entity.Member;
import com.doyouknow.project.service.LoginService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
//@SessionAttributes("seq")
public class loginController {

    @Autowired
    private LoginService loginService;
    public loginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public String login(){return "login/login";}

    @PostMapping("/login")
    public String loginOk(@RequestParam("id") String id,
                          @RequestParam("pwd") String pwd,
                          Model model,
                          RedirectAttributes rttr){
        Member member=loginService.login(id, pwd);
        if(member==null){
            rttr.addFlashAttribute("message","없는 회원이거나 입력정보가 일치하지 않습니다.");
            return "redirect:/login";
        }else if(member.getStatus()==1||member.getStatus()==0) {
            rttr.addFlashAttribute("message","아직 회원가입 승인되지 않았거나 거부된 계정입니다.");
            return "redirect:/login";
        }else{
            return "login/sucess";
        }
    }

    @GetMapping("/signup")
    public String signup(){return "login/signup";}

    @PostMapping("/signup")
    public String signupOk(@RequestParam("name") String name,
                         @RequestParam("id") String id,
                         @RequestParam("pwd") String pwd,
                         @RequestParam("phone") String phone,
                         @RequestParam("email") String email,
                         @RequestParam("deptName") String deptName){
        loginService.signup(name, id, pwd, phone, email, deptName);
        return "login/login";
    }

    @GetMapping("/searchid")
    public String searchid(){return "login/searchid";}

    @PostMapping("/searchid")
    public String searchidOk(@RequestParam("name") String name,
                             @RequestParam("email") String eamil,
                             Model model,
                             RedirectAttributes rttr){
        if(loginService.searchId(name,eamil)!=null){
            rttr.addFlashAttribute("result",loginService.searchId(name,eamil));
        }else{
            rttr.addFlashAttribute("message","없는 회원이거나 입력정보가 일치하지 않습니다.");
        }
        return "redirect:/searchid";
    }

    @GetMapping("/searchpwd")
    public String searchpwd(){return "login/searchpwd";}

    @PostMapping("/searchpwd")
    public String searchpwdOk(@RequestParam("id") String id,
                             @RequestParam("phone") String phone,
                             Model model,
                             RedirectAttributes rttr){
        if(loginService.searchPwd(id,phone)!=null){
            rttr.addFlashAttribute("result",loginService.searchPwd(id,phone));
        }else{
            rttr.addFlashAttribute("message","없는 회원이거나 입력정보가 일치하지 않습니다.");
        }
        return "redirect:/searchpwd";
    }
}



