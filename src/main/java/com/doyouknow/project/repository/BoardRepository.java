package com.doyouknow.project.repository;

import com.doyouknow.project.dto.BoardDTO;
import com.doyouknow.project.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {

//    @Query(value = "select ")
//    List<BoardDTO> findTitleContaining(String keyword);

    @Query(value = "select b from Board b where b.writerDeptSeq = :deptSeq")
    Page<Board> findAllByDept(Pageable pageable, int deptSeq);

    @Query(value = "select b from Board b where b.writerDeptSeq = :deptSeq")
    List<Board> findTop(int deptSeq, Pageable pageable);
}
