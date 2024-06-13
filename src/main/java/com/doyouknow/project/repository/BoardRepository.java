package com.doyouknow.project.repository;

import com.doyouknow.project.entity.Board;
import com.doyouknow.project.entity.Dept;
import com.doyouknow.project.entity.Member;
import org.apache.ibatis.annotations.Delete;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {

    /* 학과별 리스트 출력 */
    @Query(value = "select b from Board b where b.writerDeptSeq = :deptSeq AND (:sortPublicType = 0 or b.type = :sortPublicType)")
    Page<Board> findAllByDept(Pageable pageable, int deptSeq, int sortPublicType);

    /* 학과별 마감순 고정 값 출력 */
    @Query(value = "select b from Board b where b.writerDeptSeq = :deptSeq AND b.applyEnd > now()")
    List<Board> findDeptTop(int deptSeq, Pageable pageable);

    /* 부서별 리스트 출력 */
    @Query(value = "select b from Board b where b.type = :type")
    Page<Board> findAllByType(Pageable pageable, int type);

    /* 부서별 마감순 고정 값 출력 */
    @Query(value = "select b from Board b where b.type = :type AND b.applyEnd > now()")
    List<Board> findPublicTop(int type, Pageable pageable);

    /* 학과별 텍스트 검색 기능 */
    @Query(value = "SELECT b FROM Board b WHERE b.writerDeptSeq = :deptSeq AND (b.title LIKE %:search% OR b.content LIKE %:search%)")
    Page<Board> findAllByDeptSearch(Pageable pageable, int deptSeq, String search);

    /* 부서별 텍스트 검색 기능*/
    @Query(value = "SELECT b FROM Board b where b.type = :type AND (b.title LIKE %:search% OR b.content LIKE %:search%)")
    Page<Board> findAllByPublicSearch(Pageable pageable, int type, String search);

    @Query(value="SELECT * FROM board WHERE seq = ?", nativeQuery = true)
    Board findBoardBySeq(int seq);

    @Modifying
    @Query(value="DELETE from board where board.seq=:seq",nativeQuery = true)
    void deleteBoardBySeq(int seq);
}
