package com.doyouknow.project.repository;

import com.doyouknow.project.dto.MemberDTO;
import com.doyouknow.project.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member,Integer> {
    @Query(value = "select dept.seq from dept where dept.name=?",nativeQuery = true)
    int findDeptSeqByName(String deptName);

    @Query(value="select * from member where member.id=? and member.pwd=? ",nativeQuery = true)
    MemberDTO loginByIdPwd(String id, String pwd);

    @Query(value="select member.id from member where member.name=? and member.email=?",nativeQuery=true)
    String searchId(String name, String email);

    @Query(value="select member.pwd from member where member.id=? and member.phone=?",nativeQuery=true)
    String searchPwd(String id, String phone);

    @Query(value="select * from member where member.status=?", nativeQuery = true)
    List<Member> findMemberByType(Integer status);

    @Modifying
    @Query(value = "UPDATE member SET member.status = :status WHERE member.seq = :seq", nativeQuery = true)
    void updateMemberStatus(@Param("status") int status, @Param("seq") int seq);
}
