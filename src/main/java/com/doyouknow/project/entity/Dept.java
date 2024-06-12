package com.doyouknow.project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "dept")
@NoArgsConstructor
@Getter
@ToString
public class Dept {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;

    @Column
    private String name;

    @Column
    private String phone;

    @Column
    private String loc;

    @Column(name = "loc_detail")
    private String locDetail;

    @Column
    private String intro;

    @Column(name = "board_type")
    private Integer boardType;

    /* AllArgsConstructor */
    public Dept(int seq, String name, String phone, String loc, String locDetail, String intro, Integer boardType) {
        this.seq = seq;
        this.name = name;
        this.phone = phone;
        this.loc = loc;
        this.locDetail = locDetail;
        this.intro = intro;
        this.boardType = boardType;
    }
}
