package com.doyouknow.project.controller;

import com.doyouknow.project.entity.Board;
import com.doyouknow.project.entity.Dept;
import com.doyouknow.project.entity.Member;
import com.doyouknow.project.service.BoardService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Controller
@RequestMapping("/board")
public class BoardController2 {

    @Autowired
    private BoardService2 boardService2;


    public BoardController2(BoardService2 boardService2) {
        this.boardService2 = boardService2;
    }

    // 게시글 작성 양식 페이지
    @GetMapping("/write")
    public String write(@RequestParam(defaultValue = "0", required = false) int type,
                        @RequestParam(defaultValue = "0", required = false) int type2) {
        return "board/event-form";
    }

    @PostMapping("/event-form")
    public String writeOk(@RequestParam("type") int type, @RequestParam("type2") int type2, @RequestParam("title") String title,@RequestParam("content") String content,
                          @RequestParam("applyStartDate") LocalDate applyStartDate, @RequestParam("applyStartTime")LocalTime applyStartTime,
                          @RequestParam("applyEndDate") LocalDate applyEndDate, @RequestParam("applyEndTime")LocalTime applyEndTime,
                          @RequestParam("eventStartDate") LocalDate eventStartDate, @RequestParam("eventStartTime")LocalTime eventStartTime,
                          @RequestParam("eventEndDate") LocalDate eventEndDate, @RequestParam("eventEndTime")LocalTime eventEndTime, @RequestParam("filename") MultipartFile filename,
                          @RequestParam("calendarColor") String calendarColor, @RequestParam("loc") String loc, @SessionAttribute("seq") int seq
                          ) {
        int hit=0;
        System.out.println(seq);
        Board board1= boardService2.findBoardBySeq(seq);
        System.out.println(board1);
        Member member= boardService2.findMemberBySeq(seq);
        System.out.println(member);

        String filenameString = filename.getOriginalFilename();

        LocalDateTime applyStartDateTime = LocalDateTime.of(applyStartDate, applyStartTime);
        LocalDateTime applyEndDateTime = LocalDateTime.of(applyEndDate, applyEndTime);
        LocalDateTime eventStartDateTime = LocalDateTime.of(eventStartDate, eventStartTime);
        LocalDateTime eventEndDateTime = LocalDateTime.of(eventEndDate, eventEndTime);
        Board board=boardService2.createBoard(type, type2, title,content,hit,applyStartDateTime, applyEndDateTime, eventStartDateTime, eventEndDateTime, filenameString,calendarColor,1,seq,loc, LocalDateTime.now());
        System.out.println(board);
        return "redirect:/board/event-details/1";
    }

    // 이벤트 폼 페이지
    @GetMapping("/event-form")
    public String showEventForm() {
        return "board/event-form";
    }

    // 게시글 상세 페이지 - seq 기준
    @GetMapping("/event-details/{seq}")
    public String showEventDetails(@PathVariable("seq") int seq, Model model) {
        Board board = boardService2.findBoardBySeq(seq);
//        Member member = boardService2.findMemberBySeq(seq);
//        System.out.println(member);
        System.out.println(board);
        model.addAttribute("board", board);
        return "board/event-details";
    }
}
