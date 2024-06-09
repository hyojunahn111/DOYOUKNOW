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
import java.util.stream.Collectors;

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

    //행사, 공지 목록 보기
    public Page<BoardDTO> BoardList(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber()-1,
                pageable.getPageSize(),
                Sort.by("seq").descending());
        Page<Board> boardList = boardRepository.findAll(pageable);
        System.out.println("BoardList 호출됨. 페이지 정보: " + pageable);
        return boardList.map(board -> modelMapper.map(board, BoardDTO.class));
    }

    /* 마감일 고정 목록*/
    public List<BoardDTO> Top3List(){
        Pageable topTree = PageRequest.of(0, 3, Sort.by("applyEnd").ascending());
        Page<Board> boardPage = boardRepository.findAll(topTree);
        return boardPage.getContent().stream()
                .map(board -> modelMapper.map(board, BoardDTO.class))
                .collect(Collectors.toList());
    }
}
