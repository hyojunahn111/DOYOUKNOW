package com.doyouknow.project.controller;

import com.doyouknow.project.common.Pagenation;
import com.doyouknow.project.common.PagingButton;
import com.doyouknow.project.dto.BoardDTO;
import com.doyouknow.project.service.BoardService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("board")
public class BoardController {


    private BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    // 게시글 목록 페이지
    @GetMapping("dept/{deptSeq}")
    public String list(Model model, @PageableDefault(size = 6) Pageable pageable, @PathVariable int deptSeq) {
        Page<BoardDTO> boardList = boardService.deptBoard(pageable, deptSeq);

        /* Page */
        PagingButton paging = Pagenation.getPagingButtonInfo(boardList);

        /*마감일 고정 목록*/
        List<BoardDTO> top3 = boardService.top3List(deptSeq);

        model.addAttribute("board",boardList);
        model.addAttribute("paging",paging);
        model.addAttribute("top3", top3);

        return "board/list";
    }

    // 소개 페이지
    @GetMapping("intro")
    public String intro() {
        return "board/intro";
    }

    // 장학 페이지 테스트
    @GetMapping("scholarship")
    public String scholarship() {

        return "board/scholarship";
    }

    // 게시글 작성 양식 페이지
    @GetMapping("write")
    public String write(@RequestParam(defaultValue = "0", required = false) int type,
                        @RequestParam(defaultValue = "0", required = false) int type2) {

        return "board/event-form";
    }

    // 게시글 작성 처리
    @PostMapping("write")
    public String writeOk(BoardDTO newBoard) {
        System.out.println(newBoard);

        boardService.registerBoard(newBoard);

        return "redirect:/board/write";
    }

    @GetMapping("detail")
    public String detail(@RequestParam(defaultValue = "0", required = false) int type,
                         @RequestParam(defaultValue = "0", required = false) int type2){
        return "board/event-details";
    }

    // listMod1 사이드바 테스트 중
    @GetMapping("list-test-sidebar")
    public String testSideBar() {
        return "board/list-test-sidebar";
    }

    @GetMapping("list-test-intro")
    public String testIntro() {
        return "board/list-test-intro";
    }

    @GetMapping("list-test-intro2")
    public String testIntro2() {
        return "board/list-test-intro2";
    }
}
