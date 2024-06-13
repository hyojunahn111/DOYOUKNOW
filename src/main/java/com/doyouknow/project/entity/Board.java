package com.doyouknow.project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Entity
@Table(name = "board")
@Setter
@Getter
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
    private String loc;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    @ColumnDefault("0")
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

    @Column
    private LocalDateTime date;
}
