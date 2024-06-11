package com.doyouknow.project.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DeptDTO {

    private int seq;
    private String name;
    private String phone;
    private String loc;
    private String locDetail;
    private String intro;
}
