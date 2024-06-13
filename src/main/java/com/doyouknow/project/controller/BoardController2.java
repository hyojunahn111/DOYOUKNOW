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

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

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
                          @RequestParam("eventEndDate") LocalDate eventEndDate, @RequestParam("eventEndTime")LocalTime eventEndTime, @RequestParam("filename") MultipartFile multipartFile,
                          @RequestParam("calendarColor") String calendarColor, @RequestParam("loc") String loc
                          ) throws IOException {
        int seq=1;//@SessionAtribute("seq") int se 로 받기 전
        Member member= boardService2.findMemberBySeq(seq);
        // boardService2.uploadFile(multipartFile);

        String filename=null;

        if(!multipartFile.isEmpty()) {
            filename = multipartFile.getOriginalFilename();
        }
        System.out.println(member);

        LocalDateTime applyStartDateTime = LocalDateTime.of(applyStartDate, applyStartTime);
        LocalDateTime applyEndDateTime = LocalDateTime.of(applyEndDate, applyEndTime);
        LocalDateTime eventStartDateTime = LocalDateTime.of(eventStartDate, eventStartTime);
        LocalDateTime eventEndDateTime = LocalDateTime.of(eventEndDate, eventEndTime);

        Board board=boardService2.createBoard(type, type2, title,content,applyStartDateTime, applyEndDateTime, eventStartDateTime, eventEndDateTime, filename,calendarColor, member.getDeptSeq(), member.getSeq(), loc, LocalDateTime.now(), multipartFile);
        System.out.println(board);
        return "redirect:/board/event-details/"+board.getSeq();
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
        Member member = boardService2.findMemberBySeq(board.getWriterMemberSeq());
        Dept dept=boardService2.findDeptBySeq(board.getWriterDeptSeq());
        System.out.println(board);
        System.out.println(member);
        System.out.println(dept);
        long dayUntilEvent = ChronoUnit.DAYS.between(LocalDate.now(), board.getApplyEnd());
        System.out.println(dayUntilEvent);
        model.addAttribute("dayUntilEvent",dayUntilEvent);
        model.addAttribute("board", board);
        model.addAttribute("member", member);
        model.addAttribute("dept", dept);
        return "board/event-details";
    }

    @GetMapping("/event-delete/{seq}")
    public String deleteBoardBySeq(@PathVariable("seq")int seq){
        boardService2.deleteBoardBySeq(seq);
        return "redirect:/map";
    }

    @GetMapping("/event-out")
    public String eventOut() {
        return "redirect:/map";
    }

     @GetMapping("/event-modify/{seq}")
    public String modifyBoard(@PathVariable("seq") int seq, Model model) {
        Board board = boardService2.findBoardBySeq(seq);
        model.addAttribute("board", board);

        LocalDate applyStartDate=board.getApplyStart().toLocalDate();
        model.addAttribute("applyStartDate",applyStartDate);
        LocalTime applyStartTime=board.getApplyStart().toLocalTime();
        model.addAttribute("applyStartTime",applyStartTime);

         LocalDate applyEndDate=board.getApplyEnd().toLocalDate();
         model.addAttribute("applyEndDate",applyEndDate);
         LocalTime applyEndTime=board.getApplyEnd().toLocalTime();
         model.addAttribute("applyEndTime",applyEndTime);

         LocalDate eventStartDate=board.getEventStart().toLocalDate();
         model.addAttribute("eventStartDate",eventStartDate);
         LocalTime eventStartTime=board.getEventStart().toLocalTime();
         model.addAttribute("eventStartTime",eventStartTime);

         LocalDate eventEndDate=board.getEventEnd().toLocalDate();
         model.addAttribute("eventEndDate",eventEndDate);
         LocalTime eventEndTime=board.getEventEnd().toLocalTime();
         model.addAttribute("eventEndTime",eventEndTime);


        return "board/event-modify";
    }

    @PostMapping("/event-modify/{boardSeq}")
    public String modifyBoardOk(@PathVariable("boardSeq") int boardSeq,@RequestParam("type") int type, @RequestParam("type2") int type2, @RequestParam("title") String title,@RequestParam("content") String content,
                          @RequestParam("applyStartDate") LocalDate applyStartDate, @RequestParam("applyStartTime")LocalTime applyStartTime,
                          @RequestParam("applyEndDate") LocalDate applyEndDate, @RequestParam("applyEndTime")LocalTime applyEndTime,
                          @RequestParam("eventStartDate") LocalDate eventStartDate, @RequestParam("eventStartTime")LocalTime eventStartTime,
                          @RequestParam("eventEndDate") LocalDate eventEndDate, @RequestParam("eventEndTime")LocalTime eventEndTime, @RequestParam("filename") MultipartFile multipartFile,
                          @RequestParam("calendarColor") String calendarColor, @RequestParam("loc") String loc
    ) throws IOException {
        int seq=1;//@SessionAtribute("seq") int se 로 받기 전

        Member member= boardService2.findMemberBySeq(seq);
        // boardService2.uploadFile(multipartFile);

        String filename=null;

        if(!multipartFile.isEmpty()) {
            filename = multipartFile.getOriginalFilename();
        }
        System.out.println(member);

        LocalDateTime applyStartDateTime = LocalDateTime.of(applyStartDate, applyStartTime);
        LocalDateTime applyEndDateTime = LocalDateTime.of(applyEndDate, applyEndTime);
        LocalDateTime eventStartDateTime = LocalDateTime.of(eventStartDate, eventStartTime);
        LocalDateTime eventEndDateTime = LocalDateTime.of(eventEndDate, eventEndTime);

        Board board=boardService2.modifyBoard(boardSeq,type, type2, title,content,applyStartDateTime, applyEndDateTime, eventStartDateTime, eventEndDateTime, filename,calendarColor, member.getDeptSeq(), member.getSeq(), loc, LocalDateTime.now(), multipartFile);
        System.out.println(board);
        return "redirect:/board/event-details/"+boardSeq;
    }
}
