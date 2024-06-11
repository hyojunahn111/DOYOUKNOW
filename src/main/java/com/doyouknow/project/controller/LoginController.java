package com.doyouknow.project.controller;

import com.doyouknow.project.entity.Member;
import com.doyouknow.project.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@SessionAttributes("seq")
public class LoginController {

    @Autowired
    private LoginService loginService;
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @ModelAttribute("seq")
    public Integer seq() {
        return null;
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
        }else if(member.getStatus()==-1||member.getStatus()==0) {
            rttr.addFlashAttribute("message","아직 회원가입 승인되지 않았거나 거부된 계정입니다.");
            return "redirect:/login";
        }else{
            model.addAttribute("seq", member.getSeq());
            return "redirect:/map";
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
                         @RequestParam("deptName") String deptName,
                           RedirectAttributes rttr
                           ){
        List<Member> memberList=loginService.findAll();
        if(name.isBlank()||id.isBlank()||pwd.isBlank()||phone.isBlank()||email.isBlank()){
            rttr.addFlashAttribute("message","입력란은 비워둘수 없습니다");
            return "redirect:/signup";
        }
        for (Member member : memberList) {
            if (member.getId().equals(id)) {
                rttr.addFlashAttribute("message", "이미 존재하는 ID입니다. 다른 ID를 사용하십시오.");
                return "redirect:/signup";
            }
        }
        for (Member member : memberList) {
            if (member.getEmail().equals(email)) {
                rttr.addFlashAttribute("message", "이미 존재하는 Email입니다. 다른 Email을 사용하십시오.");
                return "redirect:/signup";
            }
        }
        if(!pwd.matches("^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$")) {
            rttr.addFlashAttribute("message", "비밀번호는 8자 이상 15자 이하이어야 하며,\n 하나 이상의 알파벳 문자, 숫자, 특수 문자를 포함해야 합니다.");
            return "redirect:/signup";
        }if(!phone.matches("\\d+")) {
            rttr.addFlashAttribute("message", "전화번호는 숫자만 입력해주십시오");
            return "redirect:/signup";
        }else {
            loginService.signup(name, id, pwd, phone, email, deptName, 1);
            rttr.addFlashAttribute("result","회원가입이 완료되었습니다.");
        }
        return "login/login";
    }

    @GetMapping("/searchid")
    public String searchid(){return "login/searchid";}

    @PostMapping("/searchid")
    public String searchidOk(@RequestParam("name") String name,
                             @RequestParam("email") String eamil,
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
                             RedirectAttributes rttr){
        if(loginService.searchPwd(id,phone)!=null){
            rttr.addFlashAttribute("result",loginService.searchPwd(id,phone));
        }else{
            rttr.addFlashAttribute("message","없는 회원이거나 입력정보가 일치하지 않습니다.");
        }
        return "redirect:/searchpwd";
    }
    @GetMapping("/logout")
    public String logout(SessionStatus status) {
        status.setComplete();
        return "redirect:/login";
    }
}



