package com.wuhanyunzhong.itass.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface DggService {

    public Integer addUser(Map map);

    public Integer test();

    public Map setest();

    public Integer insertDepart(List partList);

    public Integer addFirst(List partList);

    public Integer insertJtl(List partList);
    public Integer upData(List partList);
    public Map findByname(JSONObject dggObject);
    public Integer upToken(JSONObject dggObject);

    public List findAllde(JSONObject dggObject);


//    public Map findData(JSONObject dggObject);
//    public Map findPeakData(JSONObject dggObject);
    public Map findAvg(JSONObject dggObject);


    public Map getAllData(JSONObject dggObject);
    public Map getMini(JSONObject dggObject);
    public Map getBig(JSONObject dggObject);

    public Map getDayCompare(JSONObject dggObject);
    public Map getWeekCompare(JSONObject dggObject);

    public Map findPartCompareInfo(JSONObject dggObject);

}
