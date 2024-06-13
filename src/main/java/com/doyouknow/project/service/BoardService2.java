package com.doyouknow.project.service;

import com.doyouknow.project.entity.Board;
import com.doyouknow.project.entity.Dept;
import com.doyouknow.project.entity.Member;
import com.doyouknow.project.repository.BoardRepository;
import com.doyouknow.project.repository.DeptRepository;
import com.doyouknow.project.repository.MemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class BoardService2 {

    private ModelMapper modelMapper;
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private DeptRepository deptRepository;

    public BoardService2(ModelMapper modelMapper, BoardRepository boardRepository,MemberRepository memberRepository, DeptRepository deptRepository) {
        this.modelMapper = modelMapper;
        this.boardRepository = boardRepository;
        this.memberRepository=memberRepository;
        this.deptRepository=deptRepository;
    }


    @Transactional(rollbackFor = IOException.class)
    public Board createBoard(int type, int type2, String title, String content,
                             LocalDateTime applyStart, LocalDateTime applyEnd, LocalDateTime eventStart, LocalDateTime eventEnd,
                             String filename, String calendarColor, int writerDeptSeq, int MemberSeq, String loc, LocalDateTime date,
                             MultipartFile file) throws IOException {
        String beforeExpand = filename.substring(0, filename.indexOf("."));
        String expand = filename.substring(filename.indexOf("."));

        String finalFileName = beforeExpand + "_" + System.currentTimeMillis() + expand;

        Board board=new Board();

        board.setType(type);
        board.setType2(type2);
        board.setTitle(title);
        board.setContent(content);
        board.setApplyStart(applyStart);
        board.setApplyEnd(applyEnd);
        board.setEventStart(eventStart);
        board.setEventEnd(eventEnd);
        board.setFilename(finalFileName);
        board.setCalendarColor(calendarColor);
        board.setWriterDeptSeq(writerDeptSeq);
        board.setWriterMemberSeq(MemberSeq);
        board.setLoc(loc);
        board.setDate(date);

        Board savedBoard = new Board();
        savedBoard = boardRepository.save(board);

        if (filename != null) {
            /* throw IOException */
            uploadFile(file, savedBoard.getSeq(), finalFileName);
        }

        return savedBoard;
    }

    @Transactional(rollbackFor = IOException.class)
    public Board modifyBoard(int seq, int type, int type2, String title, String content,
                             LocalDateTime applyStart, LocalDateTime applyEnd, LocalDateTime eventStart, LocalDateTime eventEnd,
                             String filename, String calendarColor, int writerDeptSeq, int MemberSeq, String loc, LocalDateTime date,
                             MultipartFile file) throws IOException {
        String beforeExpand = filename.substring(0, filename.indexOf("."));
        String expand = filename.substring(filename.indexOf("."));

        String finalFileName = beforeExpand + "_" + System.currentTimeMillis() + expand;

        Board board=new Board();
        board.setSeq(seq);
        board.setType(type);
        board.setType2(type2);
        board.setTitle(title);
        board.setContent(content);
        board.setApplyStart(applyStart);
        board.setApplyEnd(applyEnd);
        board.setEventStart(eventStart);
        board.setEventEnd(eventEnd);
        board.setFilename(finalFileName);
        board.setCalendarColor(calendarColor);
        board.setWriterDeptSeq(writerDeptSeq);
        board.setWriterMemberSeq(MemberSeq);
        board.setLoc(loc);
        board.setDate(date);

        Board savedBoard = new Board();
        savedBoard = boardRepository.save(board);

        if (filename != null) {
            /* throw IOException */
            uploadFile(file, savedBoard.getSeq(), finalFileName);
        }

        return savedBoard;
    }


    @Transactional
    public Board findBoardBySeq(int seq) {
        return boardRepository.findBoardBySeq(seq);
    }

    @Transactional
    public Dept findDeptBySeq(int seq) {
        return deptRepository.findDeptBySeq(seq);
    }

    @Transactional
    public Member findMemberBySeq(int seq) {
        return memberRepository.findMemberBySeq(seq);
    }

    public void uploadFile(MultipartFile file, int boardSeq, String fileName) throws IOException {
        String uploadPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\img\\uploadBoardFile\\" + boardSeq;

        File fileDir = new File(uploadPath);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        File newFile = new File(uploadPath + "\\" + fileName);

        file.transferTo(newFile);
    }


    @Transactional
    public void deleteBoardBySeq(int seq){
        boardRepository.deleteBoardBySeq(seq);
    }
}
