package com.doyouknow.project.entity;

import jakarta.persistence.*;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "board")
@ToString
public class Board {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;

    @Column
    private int type;

    @Column
    private int type2;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private int hit;

    @Column(name = "apply_start")
    private LocalDateTime applyStart;

    @Column(name = "apply_end")
    private LocalDateTime applyEnd;

    @Column(name = "event_start")
    private LocalDateTime eventStart;

    @Column(name = "event_end")
    private LocalDateTime eventEnd;

    @Column
    private String filename;

    @Column(name = "calendar_color")
    private String calendarColor;

    @Column(name = "writer_dept_seq")
    private int writerDeptSeq;

    @Column(name = "writer_member_seq")
    private int writerMemberSeq;
}
