package com.doyouknow.project.controller;

import com.doyouknow.project.entity.Member;
import com.doyouknow.project.service.AdminPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminPageController {

    @Autowired
    private AdminPageService adminPageService;
    public AdminPageController(AdminPageService adminPageService) {this.adminPageService = adminPageService;}

    @GetMapping("newMemberList")
    public String printNewMemberList(Model model){
        List<Member> memberList= adminPageService.findMemberByStatus(0);
        model.addAttribute("memberList",memberList);
        return "/adminPage/newMemberList";
    }

    @PostMapping("newMemberList")
    public String updateMemberStatus(@RequestParam("action") String action, @RequestParam("seq") int memberSeq) {
        if ("allow".equals(action)) {
            adminPageService.updateMemberStatus(1, memberSeq);
        } else if ("denied".equals(action)) {
            adminPageService.updateMemberStatus(-1, memberSeq);
        }
        return "redirect:/newMemberList";
    }

    @GetMapping("oldMemberList")
    public String printOldMemberList(Model model){
        List<Member> memberList= adminPageService.findMemberByType(1);
        model.addAttribute("memberList",memberList);
        return "/adminPage/oldMemberList";
    }

    @PostMapping("oldMemberList")
    public String deleteOldMember(@RequestParam("seq") int memberSeq) {
            adminPageService.deleteMember( memberSeq);
        return "redirect:/oldMemberList";
    }

}
