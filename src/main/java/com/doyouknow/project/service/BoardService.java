package com.doyouknow.project.service;

import com.doyouknow.project.dto.BoardDTO;
import com.doyouknow.project.entity.Board;
import com.doyouknow.project.repository.BoardRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
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
    public List<BoardDTO> BoardList() {
        List<Board> boardList = boardRepository.findAll(Sort.by("seq").descending());

        return boardList.stream().map(board -> modelMapper.map(board, BoardDTO.class)).collect(Collectors.toList());
    }
}
