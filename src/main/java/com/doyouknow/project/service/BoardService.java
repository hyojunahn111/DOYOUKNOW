package com.doyouknow.project.service;

import com.doyouknow.project.dto.BoardDTO;
import com.doyouknow.project.entity.Board;
import com.doyouknow.project.repository.BoardRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardService {

    private ModelMapper modelMapper;

    private BoardRepository boardRepository;

    public BoardService(ModelMapper modelMapper, BoardRepository boardRepository) {
        this.modelMapper = modelMapper;
        this.boardRepository = boardRepository;
    }

    @Transactional
    public void registerBoard(BoardDTO newBoard) {
        System.out.println("[BoardDTO] : " + newBoard);
        Board newBoardEntity = modelMapper.map(newBoard, Board.class);
        System.out.println("[Board] : " + newBoardEntity);
        boardRepository.save(newBoardEntity);
    }

    /* 학과별 페이지 */
    public Page<BoardDTO> deptBoard(Pageable pageable, int deptSeq, String search) {
        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber()-1,
                pageable.getPageSize(),
                Sort.by("seq").descending());

        /*검색어가 입력되면 검색한 결과 보여주기 없으면 결과 보여주지 않기 */
        Page<Board> boardList;
        if(search !=null && !search.isEmpty()){
            boardList = boardRepository.findAllByDeptSearch(pageable, deptSeq, search);
        }else {
            boardList = boardRepository.findAllByDept(pageable, deptSeq);
        }
        return boardList.map(board -> modelMapper.map(board, BoardDTO.class));
    }

    /* 학과별 페이지 마감일 고정 목록*/
    public List<BoardDTO> deptTop(int deptSeq){

        Pageable pageable = PageRequest.of(0, 3, Sort.by("applyEnd").ascending());
        List<Board> result = boardRepository.findDeptTop(deptSeq, pageable);

        return result.stream().map(board -> modelMapper.map(board, BoardDTO.class)).toList();
    }

    /* 부서별 페이지 */
    public Page<BoardDTO> publicBoard(Pageable pageable, int type, String search) {
        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber()-1,
                pageable.getPageSize(),
                Sort.by("seq").descending());

        /*검색어가 입력되면 검색한 결과 보여주기 없으면 결과 보여주지 않기 */
        Page<Board> boardList;
        if(search !=null && !search.isEmpty()){
            boardList = boardRepository.findAllByPublicSearch(pageable, type, search);
        }else {
            boardList = boardRepository.findAllByType(pageable, type);
        }
        return boardList.map(board -> modelMapper.map(board, BoardDTO.class));
    }

    /* 부서별 페이지 마감일 고정 목록*/
    public List<BoardDTO> publicTop(int type){

        Pageable pageable = PageRequest.of(0, 3, Sort.by("applyEnd").ascending());
        List<Board> result = boardRepository.findPublicTop(type, pageable);

        return result.stream().map(board -> modelMapper.map(board, BoardDTO.class)).toList();
    }




}
