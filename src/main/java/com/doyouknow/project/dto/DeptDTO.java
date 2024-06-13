package com.doyouknow.project.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class DeptDTO {

    private int seq;
    private String name;
    private String phone;
    private String loc;
    private String locDetail;
    private String intro;
    private int boardType;
    private int isDivision;

    /* AllArgsConstructor */
    public DeptDTO(int seq, String name, String phone, String loc, String locDetail, String intro, int boardType, int isDisvision) {
        this.seq = seq;
        this.name = name;
        this.phone = phone;
        this.loc = loc;
        this.locDetail = locDetail;
        this.intro = intro;
        this.boardType = boardType;
        this.isDivision = isDisvision;
    }
}
