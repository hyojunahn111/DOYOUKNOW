package com.doyouknow.project.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class MemberDTO {
    private int seq;
    private int deptSeq;
    private String deptName;
    private String id;
    private String pwd;
    private String name;
    private String email;
    private String phone;
    private int status;
    private int type;

    public MemberDTO(int seq, int deptSeq, String deptName, String id, String pwd, String name, String email, String phone, int status, int type) {
        this.seq = seq;
        this.deptSeq = deptSeq;
        this.deptName = deptName;
        this.id = id;
        this.pwd = pwd;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.status = status;
        this.type = type;
    }
}
