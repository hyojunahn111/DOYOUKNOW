package com.doyouknow.project.service;

import com.doyouknow.project.entity.Board;
import com.doyouknow.project.entity.Dept;
import com.doyouknow.project.entity.Member;
import com.doyouknow.project.repository.BoardRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class BoardService2 {

    private ModelMapper modelMapper;
    private BoardRepository boardRepository;

    public BoardService2(ModelMapper modelMapper, BoardRepository boardRepository) {
        this.modelMapper = modelMapper;
        this.boardRepository = boardRepository;
    }


    @Transactional
    public Board createBoard(int type, int type2, String title, String content, int hit,
                             LocalDateTime applyStart, LocalDateTime applyEnd, LocalDateTime eventStart, LocalDateTime eventEnd,
                             String filename, String calendarColor, int writerDeptSeq, int MemberSeq, String loc, LocalDateTime date) {
    Board board=new Board();
        board.setType(type);
        board.setType2(type2);
        board.setTitle(title);
        board.setContent(content);
        board.setHit(hit);
        board.setApplyStart(applyStart);
        board.setApplyEnd(applyEnd);
        board.setEventStart(eventStart);
        board.setEventEnd(eventEnd);
        board.setFilename(filename);
        board.setCalendarColor(calendarColor);
        board.setWriterDeptSeq(writerDeptSeq);
        board.setWriterMemberSeq(MemberSeq);
        board.setLoc(loc);
        board.setDate(date);
    return boardRepository.save(board);
    }


    public Board findBoardBySeq(int seq) {
        return boardRepository.findBoardBySeq(seq);
    }

    public Dept findDeptBySeq(int seq) {
        return boardRepository.findDeptBySeq(seq);
    }

    public Member findMemberBySeq(int seq) {
        return boardRepository.findMemberBySeq(seq);
    }

  /*  @Transactional
    public void setBoard(S){
        Board board = new Board();

    }*/
}
