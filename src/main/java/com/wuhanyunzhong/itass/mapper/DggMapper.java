package com.wuhanyunzhong.itass.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface DggMapper {

    Integer addUser(Map map);

    Integer test();

    Map setest();

    //新增多个考勤点
    Integer insertDepart(@Param("list") List list);

//    添加每日第一个完整的信息
    Integer addFirst(@Param("list") List list);

    Integer insertJtl(@Param("list") List list);
}
