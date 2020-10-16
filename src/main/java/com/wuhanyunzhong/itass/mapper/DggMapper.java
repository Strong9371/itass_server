package com.wuhanyunzhong.itass.mapper;

import com.alibaba.fastjson.JSONObject;
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

    Integer addFirst(@Param("list") List list);

    Integer insertJtl(@Param("list") List list);

    Integer upData(@Param("list") List list);


    Map findByname(JSONObject regObject);
    List findRouter(JSONObject regObject);

    Integer upToken(JSONObject regObject);

    List findAllde(JSONObject regObject);
    List findData(JSONObject regObject);
    Map findPeakData(JSONObject regObject);
    Map findAvg(JSONObject regObject);
    Map findCost(JSONObject regObject);

//    日环比数据
    List findDayCompareJtlv(JSONObject regObject);
    List findDayCompareInfo(JSONObject regObject);

//    周环比数据
    List findWeekCompareJtlv(JSONObject regObject);
    List findWeekCompareInfo(JSONObject regObject);
    List findPartCompareInfo(JSONObject regObject);
    List findPartCompareInfo02(JSONObject regObject);

//设置页的相关方法
    List userByPid(JSONObject regObject);
    List departByPid(JSONObject regObject);
}
