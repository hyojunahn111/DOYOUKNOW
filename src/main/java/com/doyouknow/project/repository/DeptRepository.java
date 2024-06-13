package com.doyouknow.project.repository;

import com.doyouknow.project.entity.Dept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeptRepository extends JpaRepository<Dept, Integer> {

    List<Dept> findByBoardType(int boardType);

    @Query(value="select * from dept d", nativeQuery=true)
    List<Dept> findAll();
}
