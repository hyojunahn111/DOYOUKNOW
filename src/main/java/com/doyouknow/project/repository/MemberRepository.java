package com.doyouknow.project.repository;

import com.doyouknow.project.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<Member,Integer> {
    @Query(value = "select dept.seq from dept where dept.name=?",nativeQuery = true)
    int findDeptSeqByName(String deptName);

    @Query(value="select member.seq from member where member.id=? and member.pwd=? ",nativeQuery = true)
    Integer loginByIdPwd(String id, String pwd);

    @Query(value="select member.id from member where member.name=? and member.email=?",nativeQuery=true)
    String searchId(String name, String email);

    @Query(value="select member.pwd from member where member.id=? and member.phone=?",nativeQuery=true)
    String searchPwd(String id, String phone);
}
