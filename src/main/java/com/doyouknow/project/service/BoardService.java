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

import java.util.ArrayList;
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

        Pageable pageable = PageRequest.of(0, 5, Sort.by("applyEnd").ascending());
        // 3개의 게시물 가져오기
        List<Board> boardList = boardRepository.findDeptTop(deptSeq, pageable);

        // 가져온 게시물을 BoardDTO로 변환
        List<BoardDTO> boardDTOList = boardList.stream()
                .map(board -> modelMapper.map(board, BoardDTO.class))
                .toList();

        // 가져온 게시물 중 마감일이 지나지 않은 것들을 필터링하여 반환
        return boardDTOList.stream()
                .filter(boardDTO -> boardDTO.getSeconds() > 0 || boardDTOList.size() < 3) // 마감일이 지나지 않은 게시물이거나, 3개 미만일 경우 포함
                .limit(3) // 최대 3개까지만 반환
                .toList();
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
    public List<BoardDTO> publicTop(int type) {
        Pageable pageable = PageRequest.of(0, 5, Sort.by("applyEnd").ascending());
        // 3개의 게시물 가져오기
        List<Board> boardList = boardRepository.findPublicTop(type, pageable);

        // 가져온 게시물을 BoardDTO로 변환
        List<BoardDTO> boardDTOList = boardList.stream()
                .map(board -> modelMapper.map(board, BoardDTO.class))
                .toList();

/* modelMapper = 어떠한 클래스(1번 클래스 = Board)가 존재할 때 일부 필드의 자료형과 이름이 겹치는 다른 클래스(2번 클래스 = BoardDTO)가 존재할 때
	1번 클래스의 필드 데이터를 2번 클래스 인스턴스를 생성함과 동시에 동일한 필드로 값을 매핑하여 할당한다.*/
            /* 예시 */
//        for(BoardDTO boardDTO : boardDTOList) {
//            if(boardDTO.getSeconds() > 0) {
//                // 아무 코드
//            }
//        }


            // 가져온 게시물 중 마감일이 지나지 않은 것들을 필터링하여 반환
            return boardDTOList.stream()
                    .filter(boardDTO -> boardDTO.getSeconds() > 0 || boardDTOList.size() < 3) // 마감일이 지나지 않은 게시물이거나, 3개 미만일 경우 포함
                    .limit(3) // 최대 3개까지만 반환
                    .toList();
        }




}
