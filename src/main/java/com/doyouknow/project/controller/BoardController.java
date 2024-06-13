package com.doyouknow.project.controller;

import com.doyouknow.project.common.Pagenation;
import com.doyouknow.project.common.PagingButton;
import com.doyouknow.project.dto.BoardDTO;
import com.doyouknow.project.dto.DeptDTO;
import com.doyouknow.project.service.BoardService;
import com.doyouknow.project.service.DeptService;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("board")
public class BoardController {


    private BoardService boardService;
    private DeptService deptService;

    public BoardController(BoardService boardService, DeptService deptService) {
        this.boardService = boardService;
        this.deptService = deptService;
    }

    // 장학 게시글 목록 페이지
    @GetMapping("dept/{deptSeq}")
    public String list(Model model, @PageableDefault(size = 6) Pageable pageable, @PathVariable int deptSeq,
                       @RequestParam(required = false) String search,
                       @RequestParam(required = false, defaultValue = "1") int sortOrder,
                       @RequestParam(required = false, defaultValue = "0") int sortPublicType) {

        /* 일반 정렬 */
        String strSortOrder = "seq";
        Sort sort = Sort.by(strSortOrder).descending();

        switch(sortOrder) {
            case 1:
                strSortOrder = "seq";
                sort = Sort.by(strSortOrder).descending();
                break;
            case 2:
                strSortOrder = "hit";
                sort = Sort.by(strSortOrder).descending();
                break;
            case 3:
                strSortOrder = "applyEnd";
                sort = Sort.by(strSortOrder);
                break;
            case 4:
                strSortOrder = "eventStart";
                sort = Sort.by(strSortOrder);
                break;
            default:
        }

        DeptDTO deptInfo = deptService.findBySeq(deptSeq);

        model.addAttribute("deptInfo", deptInfo);

        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber()-1,
                pageable.getPageSize(),
                sort);

        Page<BoardDTO> boardList = boardService.deptBoard(pageable, deptSeq, search, sortPublicType);

        /* Page */
        PagingButton paging = Pagenation.getPagingButtonInfo(boardList);

        /*마감일 고정 목록*/
        List<BoardDTO> top3 = boardService.deptTop(deptSeq);

        model.addAttribute("deptInfo", deptInfo);
        model.addAttribute("board",boardList);
        model.addAttribute("paging",paging);
        model.addAttribute("boardType", "dept");
        model.addAttribute("boardValue", deptSeq);
        model.addAttribute("top3", top3);
        model.addAttribute("sortOrder", sortOrder);
        model.addAttribute("sortPublicType", sortPublicType);

        return "board/list";
    }

    // 부서 페이지
    @GetMapping("public/{type}")
    public String publiclist(Model model, @PageableDefault(size = 6) Pageable pageable, @PathVariable int type,
                             @RequestParam(required = false) String search,
                             @RequestParam(required = false, defaultValue = "1") int sortOrder) {
        String strSortOrder = "seq";
        Sort sort = Sort.by(strSortOrder).descending();

        switch(sortOrder) {
            case 1:
                strSortOrder = "seq";
                sort = Sort.by(strSortOrder).descending();
                break;
            case 2:
                strSortOrder = "hit";
                sort = Sort.by(strSortOrder).descending();
                break;
            case 3:
                strSortOrder = "applyEnd";
                sort = Sort.by(strSortOrder);
                break;
            case 4:
                strSortOrder = "eventStart";
                sort = Sort.by(strSortOrder);
                break;
            default:
        }

        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber()-1,
                pageable.getPageSize(),
                sort);

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
        model.addAttribute("sortOrder", sortOrder);


        return "board/list";
    }

    // 소개 페이지
/*    @GetMapping("dept/{boardValue}/intro")
    public String intro(Model model, @PathVariable int boardValue) {

        DeptDTO deptInfo = deptService.findBySeq(boardValue);

        model.addAttribute("deptInfo", deptInfo);
        model.addAttribute("boardType", "dept");
        model.addAttribute("boardValue", boardValue);

        return "board/intro";

    }*/


    // 소개 페이지
    @GetMapping("{boardType}/{boardValue}/intro")
    public String intro2(Model model
            , @PathVariable String boardType
            , @PathVariable int boardValue) throws ParseException {

        List<DeptDTO> deptList = null;

        if( boardType.equals("dept") ) {
            DeptDTO deptInfo = deptService.findBySeq(boardValue);

            deptList = new ArrayList<>();
            deptList.add(deptInfo);

        }else if( boardType.equals("public") ){
            deptList = deptService.findByBoardType(boardValue);

        }else {
            return "redirect:/error";
        }

        model.addAttribute("boardType", boardType);
        model.addAttribute("boardValue", boardValue);
        model.addAttribute("deptList", deptList);

        return "board/intro3";
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
