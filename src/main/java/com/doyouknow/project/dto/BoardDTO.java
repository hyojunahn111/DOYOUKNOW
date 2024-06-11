package com.doyouknow.project.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class BoardDTO {

    private int seq;

    private int type;

    private int type2;

    private String loc;

    private String title;

    private String content;

    private int hit;

    private LocalDateTime applyStart;

    private LocalDateTime applyEnd;

    private LocalDateTime eventStart;

    private LocalDateTime eventEnd;

    private String filename;

    private String calendarColor;

    private int writerDeptSeq;

    private int writerMemberSeq;

    private LocalDateTime date;


    /* AllArgsConstructor */

    public BoardDTO(int seq, int type, int type2, String loc, String title, String content, int hit, LocalDateTime applyStart, LocalDateTime applyEnd, LocalDateTime eventStart, LocalDateTime eventEnd, String filename, String calendarColor, int writerDeptSeq, int writerMemberSeq, LocalDateTime date) {
        this.seq = seq;
        this.type = type;
        this.type2 = type2;
        this.loc = loc;
        this.title = title;
        this.content = content;
        this.hit = hit;
        this.applyStart = applyStart;
        this.applyEnd = applyEnd;
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
        this.filename = filename;
        this.calendarColor = calendarColor;
        this.writerDeptSeq = writerDeptSeq;
        this.writerMemberSeq = writerMemberSeq;
        this.date = date;
    }

    /* 마감일 D-day 용*/
    public long getDday() {
        return ChronoUnit.DAYS.between(LocalDateTime.now(), this.applyEnd);
    }

    /* 마감 표시용 */
    public long getSeconds() {
        return ChronoUnit.SECONDS.between(LocalDateTime.now(), this.applyEnd);
    }

}