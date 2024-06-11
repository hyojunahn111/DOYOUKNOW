package com.doyouknow.project.service;

import com.doyouknow.project.dto.DeptDTO;
import com.doyouknow.project.entity.Dept;
import com.doyouknow.project.repository.DeptRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptService {

    private ModelMapper modelMapper;

    private DeptRepository deptRepository;

    public DeptService(ModelMapper modelMapper, DeptRepository deptRepository) {
        this.modelMapper = modelMapper;
        this.deptRepository = deptRepository;
    }

    public DeptDTO findBySeq(int seq) {

        Dept dept = deptRepository.findById(seq).orElseThrow(IllegalArgumentException::new);

        return modelMapper.map(dept, DeptDTO.class);
    }

    public List<DeptDTO> findByBoardType(int boardType) {

        List<Dept> deptList = deptRepository.findByBoardType(boardType);

        return deptList.stream().map(dept -> modelMapper.map(dept, DeptDTO.class)).toList();
    }
}
