package com.doyouknow.project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name="member")
@ToString
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="seq")
    private int seq;
    @Column(name="dept_seq")
    private int deptSeq;
    @Column(name="dept_name")
    private String deptName;
    @Column(name="id")
    private String id;
    @Column(name="pwd")
    private String pwd;
    @Column(name="name")
    private String name;
    @Column(name="email")
    private String email;
    @Column(name="phone")
    private String phone;
    @Column(name="status")
    private int status;
    @Column(name="type")
    @ColumnDefault("1")
    private int type;
}
