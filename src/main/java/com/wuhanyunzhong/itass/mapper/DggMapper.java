package com.wuhanyunzhong.itass.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface DggMapper {
    Integer test();

    Map setest();

    //新增多个考勤点
    Integer insertDepart(@Param("list") List list);
}
