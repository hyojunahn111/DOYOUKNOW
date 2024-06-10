package com.doyouknow.project.mapper;

import com.doyouknow.project.dto.DeptDTO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MapMapper {

    @Select("SELECT name,phone,loc_detail,intro FROM dept WHERE loc=#{locDetail}")
    List<DeptDTO> selectByLocDetail(String locDetail);

}
