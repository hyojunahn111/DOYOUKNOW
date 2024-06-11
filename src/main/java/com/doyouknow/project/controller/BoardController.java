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

    // 부서 게시글 목록 페이지
    @GetMapping("dept/{deptSeq}")
    public String list(Model model, @PageableDefault(size = 6) Pageable pageable, @PathVariable int deptSeq,
                       @RequestParam(required = false) String search) {
        Page<BoardDTO> boardList = boardService.deptBoard(pageable, deptSeq, search);

        /* Page */
        PagingButton paging = Pagenation.getPagingButtonInfo(boardList);

        /*마감일 고정 목록*/
        List<BoardDTO> top3 = boardService.deptTop(deptSeq);

        model.addAttribute("board",boardList);
        model.addAttribute("paging",paging);
        model.addAttribute("boardType", "dept");
        model.addAttribute("boardValue", deptSeq);
        model.addAttribute("top3", top3);

        return "board/list";
    }

    // 취업, 장학 페이지
    @GetMapping("public/{type}")
    public String publiclist(Model model, @PageableDefault(size = 6) Pageable pageable, @PathVariable int type,
                             @RequestParam(required = false) String search) {
        Page<BoardDTO> boardList = boardService.publicBoard(pageable, type, search);

        /* Page */
        PagingButton paging = Pagenation.getPagingButtonInfo(boardList);

        /*마감일 고정 목록*/
        List<BoardDTO> top3 = boardService.publicTop(type);

        model.addAttribute("board",boardList);
        model.addAttribute("paging",paging);
        model.addAttribute("top3", top3);
        model.addAttribute("boardType", "public");
        model.addAttribute("boardValue", type);
        return "board/list";
    }



    // 소개 페이지
    @GetMapping("intro")
    public String intro() {
        return "board/intro";
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
}
