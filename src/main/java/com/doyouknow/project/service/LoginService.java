package com.doyouknow.project.service;

import com.doyouknow.project.dto.MemberDTO;
import com.doyouknow.project.entity.Member;
import com.doyouknow.project.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private ModelMapper modelMapper;

    @Autowired
    private MemberRepository memberRepository;

    public LoginService(ModelMapper modelMapper, MemberRepository memberRepository){
        this.modelMapper=modelMapper;
        this.memberRepository=memberRepository;
    }

    @Transactional
    public Member signup(String name, String id, String pwd, String phone, String email, String deptName) {
        Member member = new Member();
        member.setName(name);
        member.setId(id);
        member.setPwd(pwd);
        member.setPhone(phone);
        member.setEmail(email);
        member.setDeptName(deptName);
        member.setDeptSeq(memberRepository.findDeptSeqByName(deptName));
        return memberRepository.save(member);
    }

    @Transactional
    public Member login(String id, String pwd){
        return memberRepository.loginByIdPwd(id,pwd);
    }

    @Transactional
    public String searchId(String name, String email){
        return memberRepository.searchId(name, email);
    }

    @Transactional
    public String searchPwd(String id, String phone){
        return memberRepository.searchPwd(id, phone);
    }
}

