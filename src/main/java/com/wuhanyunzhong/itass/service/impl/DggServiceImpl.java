package com.wuhanyunzhong.itass.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wuhanyunzhong.itass.mapper.DggMapper;
import com.wuhanyunzhong.itass.service.DggService;
import com.wuhanyunzhong.itass.service.exception.DggException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@CacheConfig()
@Service
public class DggServiceImpl implements DggService {

    @Autowired
    DggMapper dggMapper;


    @Override
    public Integer addUser(Map map) {
        Integer integer = dggMapper.addUser(map);
        return integer;
    }

    @Override
    public Integer test() {
        Integer test = dggMapper.test();
        return test;
    }

    @Override
    public Map setest() {
        Map setest = dggMapper.setest();
        return setest;
    }

    @Override
    public Integer insertDepart(List partList) {
        Integer integer = dggMapper.insertDepart(partList);
        return integer;
    }

    @Override
    public Integer addFirst(List partList) {
        Integer integer = dggMapper.addFirst(partList);
        return integer;
    }

    @Override
    public Integer insertJtl(List partList) {
        Integer integer = dggMapper.insertJtl(partList);
        return integer;
    }

    @Override
//    @CacheEvict(value = "jtlPeak")
    public Integer upData(List partList) {
        Integer integer = dggMapper.upData(partList);
        return integer;
    }

    @Override
    public Map findByname(JSONObject dggObject) {
        Map byname = dggMapper.findByname(dggObject);
        return byname;
    }

    @Override
    public List findRouter(JSONObject dggObject) {
        List<Map> router = dggMapper.findRouter(dggObject);

        List children = new LinkedList();
        for(Map map : router){
            Map temp = new HashMap();
            String[] children1 = map.get("children").toString().split(",");
            List<String> list = Arrays.asList(children1);
            temp.put("router",map.get("router"));
            temp.put("children",list);
            children.add(temp);
        }
        Map result = new HashMap();
        result.put("router","root");
        result.put("children",children);
        List res = new LinkedList();
        res.add(result);
        return res;
    }

    @Override
    public Integer upToken(JSONObject dggObject) {
        Integer integer = dggMapper.upToken(dggObject);
        if (integer < 1) {
            throw new DggException("登录失败，请重新尝试！");
        }
        return integer;
    }


    @Override
    @Cacheable(value = "allDepart", key = "#root.methodName")
    public List findAllde(JSONObject dggObject) {
        List allde = dggMapper.findAllde(dggObject);
        return allde;
    }
//    @Override
//    @Cacheable(value = "jtlPart",key = "#dggObject.getString('pname') +#dggObject.getString('h')")
//    public Map findData(JSONObject dggObject) {
//        Map result = new HashMap();
//        List<Map> data = dggMapper.findData(dggObject);
//        Map costLimit = dggMapper.findCost(dggObject);
//        List<Map> jietongliang = new LinkedList<>();
////        30s、60s的相关数据
//        List<Map> secliang = new LinkedList<>();
////        总费用
//        double costAmount = 0;
//
//        List<Map> tonghuatime = new LinkedList<>();
//
//        for(Map map : data){
////            电话量临时数据
//            Map dhl = new HashMap();
////            接通量临时数据
//            Map jtl = new HashMap();
//
//            //            30s电话量临时数据
//            Map s30 = new HashMap();
////            60s电话量临时数据
//            Map s60 = new HashMap();
//
////          通话时长数据
//            Map thTime = new HashMap();
//
//            dhl.put("type","电话量");
//            dhl.put("depart",map.get("sname"));
//            dhl.put("amount",map.get("phoneAmount"));
//
//            jtl.put("type","接通量");
//            jtl.put("depart",map.get("sname"));
//            jtl.put("amount",map.get("phoneDone"));
////            添加费用信息
////            低于60秒的通话量，按照一分钟计算话费
//            double costFix = Double.parseDouble( map.get("costFix").toString());
//            int up60s = (int)map.get("amount60");
//            int down60s = (int)map.get("phoneDone") - up60s;
//            double oneCost =  costFix * (up60s*5 + down60s);
//            jtl.put("count", oneCost);
//            costAmount += oneCost;
//
//            s30.put("sec","30s");
//            s30.put("dep",map.get("sname"));
//            s30.put("num",map.get("amount30"));
//
//            s60.put("sec","60s");
//            s60.put("dep",map.get("sname"));
//            s60.put("num",map.get("amount60"));
//
//            thTime.put("depart",map.get("sname"));
//            thTime.put("type","时长");
//            thTime.put("timeamount",map.get("phoneTime"));
//
//
//            jietongliang.add(dhl);
//            jietongliang.add(jtl);
//
//            secliang.add(s30);
//            secliang.add(s60);
//
//            tonghuatime.add(thTime);
//
//        }
//        costLimit.put("costAmount",costAmount);
//
//        result.put("jietongliang",jietongliang);
//        result.put("secliang",secliang);
////        result.put("secOther",amount30+"/"+amount60);
//        result.put("tonghuatime",tonghuatime);
//        result.put("costLimit",costLimit);
//
//
//        return result;
//    }
//
//    @Override
//    @Cacheable(value = "jtlPeak",key = "#dggObject.getString('pname') +#dggObject.getString('h')")
//    public Map findPeakData(JSONObject dggObject) {
//        Map peakData = new HashMap();
//        List<Map> jietonglv = new LinkedList<>();
//
//        if(dggMapper.findPeakData(dggObject) != null){
//            peakData = dggMapper.findPeakData(dggObject);
//            if(peakData.containsKey("10:00")){
//                Map temp = new HashMap();
//                temp.put("timeSt","10:00");
//                temp.put("type","接通量");
//                temp.put("nowjtl",peakData.get("10:00"));
//                jietonglv.add(temp);
//            }
//
//            if(peakData.containsKey("11:00")){
//                Map temp = new HashMap();
//                temp.put("timeSt","11:00");
//                temp.put("type","接通量");
//                temp.put("nowjtl",peakData.get("11:00"));
//                jietonglv.add(temp);
//            }
//
//            if(peakData.containsKey("12:00")){
//                Map temp = new HashMap();
//                temp.put("timeSt","12:00");
//                temp.put("type","接通量");
//                temp.put("nowjtl",peakData.get("12:00"));
//                jietonglv.add(temp);
//            }
//
//            if(peakData.containsKey("15:00")){
//                Map temp = new HashMap();
//                temp.put("timeSt","15:00");
//                temp.put("type","接通量");
//                temp.put("nowjtl",peakData.get("15:00"));
//                jietonglv.add(temp);
//            }
//
//            if(peakData.containsKey("16:00")){
//                Map temp = new HashMap();
//                temp.put("timeSt","16:00");
//                temp.put("type","接通量");
//                temp.put("nowjtl",peakData.get("16:00"));
//                jietonglv.add(temp);
//            }
//
//            if(peakData.containsKey("17:00")){
//                Map temp = new HashMap();
//                temp.put("timeSt","17:00");
//                temp.put("type","接通量");
//                temp.put("nowjtl",peakData.get("17:00"));
//                jietonglv.add(temp);
//            }
//
//            if(peakData.containsKey("18:00")){
//                Map temp = new HashMap();
//                temp.put("timeSt","18:00");
//                temp.put("type","接通量");
//                temp.put("nowjtl",peakData.get("18:00"));
//                jietonglv.add(temp);
//            }
//            if(peakData.containsKey("20:00")){
//                Map temp = new HashMap();
//                temp.put("timeSt","20:00");
//                temp.put("type","接通量");
//                temp.put("nowjtl",peakData.get("20:00"));
//                jietonglv.add(temp);
//            }
//        }
//        Map jtlMap = new HashMap();
//        jtlMap.put("jietonglv",jietonglv);
//        jtlMap.put("otherData",peakData);
//        return jtlMap;
//    }

    @Override
    public Map findAvg(JSONObject dggObject) {
        Map avg = dggMapper.findAvg(dggObject);
        return avg;

    }

    /**
     * 查询第一个视图的峰值及合计数据
     *
     * @param dggObject
     * @return
     */
    @Override
    public Map getAllData(JSONObject dggObject) {

        Map peakAndAmountData = dggMapper.findPeakData(dggObject);
        List<Map> viewData = dggMapper.findData(dggObject);
        Map costData = dggMapper.findCost(dggObject);
        Map avgData = dggMapper.findAvg(dggObject);

        Map minmap = anMinView(peakAndAmountData, viewData, costData, avgData);
        Map bigmap = anBigView(viewData);

        Map result = new HashMap();
        result.put("mini", minmap);
        result.put("big", bigmap);
        return result;

    }

    /**
     * 查询第一个视图的峰值及合计数据
     *
     * @param dggObject
     * @return
     */
    @Override
    public Map getMini(JSONObject dggObject) {

        Map peakAndAmountData = dggMapper.findPeakData(dggObject);
        List<Map> viewData = dggMapper.findData(dggObject);
        Map costData = dggMapper.findCost(dggObject);
        Map avgData = dggMapper.findAvg(dggObject);

        Map minmap = anMinView(peakAndAmountData, viewData, costData, avgData);
//        Map bigmap = anBigView(viewData);

        Map result = new HashMap();
        result.put("mini", minmap);
//        result.put("big",bigmap);
        return result;

    }

    /**
     * 查询第一个视图的峰值及合计数据
     *
     * @param dggObject
     * @return
     */
    @Override
    public Map getBig(JSONObject dggObject) {
        Map peakAndAmountData = dggMapper.findPeakData(dggObject);
        List<Map> viewData = dggMapper.findData(dggObject);
        Map costData = dggMapper.findCost(dggObject);
        Map avgData = dggMapper.findAvg(dggObject);

        Map bigmap = anBigView(viewData);

        Map result = new HashMap();

        result.put("big", bigmap);
        return result;

    }

    @Override
    public Map getDayCompare(JSONObject dggObject) {
        Map dayCompareData = new HashMap();

        List huanbiV1Data = new LinkedList();
        List huanbiV2Data = new LinkedList();
        List huanbiV3Data = new LinkedList();
        List huanbiV4Data = new LinkedList();

        List<Map> dayCompareJtlv = dggMapper.findDayCompareJtlv(dggObject);
        List<Map> dayCompareInfo = dggMapper.findDayCompareInfo(dggObject);


//        合计信息
        for (Map map : dayCompareJtlv) {
//            分析第一个图的合计信息
            Map temp = new HashMap();
            temp.put("month", map.get("changeDate").toString().replaceFirst("-", ""));
            temp.put("city", "分公司合计");
            BigDecimal jtl = new BigDecimal(Double.parseDouble(map.get("jtl").toString()));
            double temperature = jtl.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            temp.put("temperature", temperature);

            huanbiV1Data.add(temp);

//            分析第三个图的信息
            Map temp01 = new HashMap();
            temp01.put("label", map.get("changeDate").toString().replaceFirst("-", ""));
            temp01.put("type", "总接通");
            BigDecimal phoneDone = new BigDecimal(Double.parseDouble(map.get("phoneDone") != null ? map.get("phoneDone").toString() : "0"));
//            double amount30 = s30.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            temp01.put("value", phoneDone);
            huanbiV3Data.add(temp01);


            Map temp02 = new HashMap();
            temp02.put("label", map.get("changeDate").toString().replaceFirst("-", ""));
            temp02.put("type", "30s");
            BigDecimal s30 = new BigDecimal(Double.parseDouble(map.get("amount30") != null ? map.get("amount30").toString() : "0"));
//            double amount30 = s30.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            temp02.put("value", s30);
            huanbiV3Data.add(temp02);

            Map temp03 = new HashMap();
            temp03.put("label", map.get("changeDate").toString().replaceFirst("-", ""));
            temp03.put("type", "60s");
            BigDecimal s60 = new BigDecimal(Double.parseDouble(map.get("amount60") != null ? map.get("amount60").toString() : "0"));
//            int amount60 = map.get("amount60") != null ?  (int)map.get("amount60") : 0;
            temp03.put("value", s60);
            huanbiV3Data.add(temp03);


        }

        for (Map map : dayCompareInfo) {
//            分析第一个视图的部门数据
            Map temp = new HashMap();
            temp.put("month", map.get("changeDate").toString().replaceFirst("-", ""));
            temp.put("city", map.get("sname"));
            BigDecimal jtl = new BigDecimal(Double.parseDouble(map.get("jtl") != null ? map.get("jtl").toString() : "0"));
            double temperature = jtl.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            temp.put("temperature", temperature);
//            temp.put("city",Double.parseDouble(map.get("jtl").toString()));
            huanbiV1Data.add(temp);


//            分析第二个视图的数据
            Map temp02 = new HashMap();
            temp02.put("name", map.get("sname"));
            int phoneAmount = map.get("pdavg") != null ? (int) map.get("pdavg") : 0;
            temp02.put("接通量", phoneAmount);
            temp02.put("时间", map.get("changeDate").toString().replaceFirst("-", ""));
            huanbiV2Data.add(temp02);


//            分析第四个图的数据
            Map temp04 = new HashMap();
            temp04.put("name", map.get("sname"));
            int phoneTime = map.get("phoneTime") != null ? (int) map.get("phoneTime") : 0;
            temp04.put("接通量", phoneTime);
            temp04.put("时间", map.get("changeDate").toString().replaceFirst("-", ""));
            huanbiV4Data.add(temp04);

        }
        dayCompareData.put("huanbiV1Data", huanbiV1Data);
        dayCompareData.put("huanbiV2Data", huanbiV2Data);
        dayCompareData.put("huanbiV3Data", huanbiV3Data);
        dayCompareData.put("huanbiV4Data", huanbiV4Data);
        return dayCompareData;
    }


    @Override
    public Map getWeekCompare(JSONObject dggObject) {
        Map weekCompareData = new HashMap();

        List zhuanbiV1Data = new LinkedList();
        List zhuanbiV2Data = new LinkedList();
        List zhuanbiV3Data = new LinkedList();
        List zhuanbiV4Data = new LinkedList();
        List<Map> dayCompareJtlv = dggMapper.findWeekCompareJtlv(dggObject);
        List<Map> dayCompareInfo = dggMapper.findWeekCompareInfo(dggObject);

//        合计信息
        for (Map map : dayCompareJtlv) {
//            分析第一个图的合计信息
            Map temp = new HashMap();
            temp.put("month", map.get("weeks"));
            temp.put("city", "分公司合计");
            BigDecimal jtl = new BigDecimal(Double.parseDouble(map.get("jtl").toString()));
            double temperature = jtl.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            temp.put("temperature", temperature);

            zhuanbiV1Data.add(temp);

//            分析第三个图的信息
            Map temp01 = new HashMap();
            temp01.put("label", map.get("weeks"));
            temp01.put("type", "总接通");
            BigDecimal phoneDone = new BigDecimal(Double.parseDouble(map.get("phoneDone") != null ? map.get("phoneDone").toString() : "0"));
//            double amount30 = s30.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            temp01.put("value", phoneDone);
            zhuanbiV3Data.add(temp01);


            Map temp02 = new HashMap();
            temp02.put("label", map.get("weeks"));
            temp02.put("type", "30s");
            BigDecimal s30 = new BigDecimal(Double.parseDouble(map.get("amount30") != null ? map.get("amount30").toString() : "0"));
//            double amount30 = s30.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            temp02.put("value", s30);
            zhuanbiV3Data.add(temp02);

            Map temp03 = new HashMap();
            temp03.put("label", map.get("weeks"));
            temp03.put("type", "60s");
            BigDecimal s60 = new BigDecimal(Double.parseDouble(map.get("amount60") != null ? map.get("amount60").toString() : "0"));
//            int amount60 = map.get("amount60") != null ?  (int)map.get("amount60") : 0;
            temp03.put("value", s60);
            zhuanbiV3Data.add(temp03);


        }

        for (Map map : dayCompareInfo) {
//            分析第一个视图的部门数据
            Map temp = new HashMap();
            temp.put("month", map.get("weeks"));
            temp.put("city", map.get("sname"));
            BigDecimal jtl = new BigDecimal(Double.parseDouble(map.get("jtl") != null ? map.get("jtl").toString() : "0"));
            double temperature = jtl.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            temp.put("temperature", temperature);
//            temp.put("city",Double.parseDouble(map.get("jtl").toString()));
            zhuanbiV1Data.add(temp);


//            分析第二个视图的数据
            Map temp02 = new HashMap();
            temp02.put("name", map.get("sname"));
            BigDecimal phoneAmount = new BigDecimal(Double.parseDouble(map.get("pdavg") != null ? map.get("pdavg").toString() : "0"));
            double amount = phoneAmount.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            temp02.put("接通量", amount);
            temp02.put("时间", map.get("weeks"));
            zhuanbiV2Data.add(temp02);


//            分析第四个图的数据
            Map temp04 = new HashMap();
            temp04.put("name", map.get("sname"));
            BigDecimal phoneTime = new BigDecimal(Double.parseDouble(map.get("phoneTime") != null ? map.get("phoneTime").toString() : "0"));
            double time = phoneTime.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            temp04.put("接通量", time);
            temp04.put("时间", map.get("weeks"));
            zhuanbiV4Data.add(temp04);

        }
        weekCompareData.put("zhuanbiV1Data", zhuanbiV1Data);
        weekCompareData.put("zhuanbiV2Data", zhuanbiV2Data);
        weekCompareData.put("zhuanbiV3Data", zhuanbiV3Data);
        weekCompareData.put("zhuanbiV4Data", zhuanbiV4Data);
        return weekCompareData;
    }

    @Override
    public Map findPartCompareInfo(JSONObject dggObject) {
        Map partCompareData = new HashMap();

        List outV1Data01all = new LinkedList();
        List outV1Data01part = new LinkedList();

//        接通率排名
        List outV2Data = new LinkedList();
//        接通量排名
        List outV3Data = new LinkedList();
//        60秒接通排名
        List outV4Data = new LinkedList();
        Map outAllData = new HashMap();

        List<Map> partCompareInfo = dggMapper.findPartCompareInfo(dggObject);
//        List<Map> findPartCompareInfo02 = dggMapper.findPartCompareInfo02(dggObject);

//        分析外比第一个视图的数据
        for (Map map : partCompareInfo) {
            Map temp = new HashMap();
            temp.put("month", map.get("changeDate").toString().replaceFirst("-", ""));
            temp.put("city", map.get("fname"));
            String jtlSt = map.get("jtl") != null ? map.get("jtl").toString() : "0";
            BigDecimal jtl = new BigDecimal(Double.parseDouble(jtlSt));
            double temperature = jtl.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            temp.put("temperature", temperature);

            Map v2 = new HashMap();
            Map v3 = new HashMap();
            Map v4 = new HashMap();
            Map v5 = new HashMap();

            if ("合计".equals(map.get("fname").toString())) {
                outV1Data01all.add(temp);
                if (dggObject.get("viewDate").toString().equals(map.get("changeDate").toString())) {
                    v2.put("name", map.get("fname"));
                    v2.put("total", jtl);
                    outAllData.put("jtlAll", map);
                }
            } else {
                outV1Data01part.add(temp);
                if (dggObject.get("viewDate").toString().equals(map.get("changeDate").toString())) {
                    v2.put("name", map.get("fname"));
                    v2.put("total", jtl);
                    outV2Data.add(v2);


                    v3.put("country", map.get("fname"));
                    v3.put("population", Double.parseDouble((map.get("phoneDone") != null ? map.get("phoneDone") : 0).toString()));
                    outV3Data.add(v3);

                    v4.put("country", map.get("fname"));
                    v4.put("amount", map.get("amount30") != null ? map.get("amount30") : 0);
                    v4.put("type", "30s");

                    v5.put("country", map.get("fname"));
                    v5.put("amount", map.get("amount60") != null ? map.get("amount60") : 0);
                    v5.put("type", "60s");
                    outV4Data.add(v4);
                    outV4Data.add(v5);
                }
            }

        }
        //第一个视图合计加上其他
        outV1Data01all.addAll(outV1Data01part);

//        第二个数据排序
        Collections.sort(outV2Data, new Comparator<Map>() {

            public int compare(Map o1, Map o2) {
                BigDecimal l1bd = new BigDecimal(Double.parseDouble(o1.get("total").toString()) * 1000);
                double l1d = l1bd.setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue();
                int l1 = (int) l1d;
                BigDecimal l2bd = new BigDecimal(Double.parseDouble(o2.get("total").toString()) * 1000);
                double l2d = l2bd.setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue();
                int l2 = (int) l2d;
                return l2 - l1;
            }
        });

        Collections.sort(outV3Data, new Comparator<Map>() {

            public int compare(Map o1, Map o2) {
                double dl1 = Double.parseDouble(o1.get("population").toString());
                double dl2 = Double.parseDouble(o2.get("population").toString());
                int l1 = (int) dl1;
                int l2 = (int) dl2;
                return l2 - l1;
            }
        });

        Collections.sort(outV4Data, new Comparator<Map>() {

            public int compare(Map o1, Map o2) {
                double dl1 = Double.parseDouble(o1.get("amount").toString());
                double dl2 = Double.parseDouble(o2.get("amount").toString());
                int l1 = (int) dl1;
                int l2 = (int) dl2;
                return l2 - l1;
            }
        });


        partCompareData.put("outV1Data", outV1Data01all);
        partCompareData.put("outV2Data", outV2Data);
        partCompareData.put("outV3Data", outV3Data);
        partCompareData.put("outV4Data", outV4Data);

        partCompareData.put("outAllData", outAllData);
        return partCompareData;
    }

    @Override
    public Map getSet(JSONObject dggObject) {
        List<Map> userByPid = dggMapper.userByPid(dggObject);
        if(userByPid.size() >= 1){
            int key = 0;
            for(Map map:userByPid){
                key ++;
                map.put("editable",false) ;
                map.put("key",key) ;
                switch((int)map.get("isadmin")){
                    case 0:
                        map.put("isadmin","普通成员");
                        break;
                    case 1:
                        map.put("isadmin","分公司管理员");
                        break;
                    case 2:
                        map.put("isadmin","系统管理员");
                        break;
                }
            }
        }
        List departByPid = dggMapper.departByPid(dggObject);
        Map getSet = new HashMap();
        getSet.put("userByPid",userByPid);
        getSet.put("departByPid",departByPid);
        return getSet;
    }

    //    小图分析方法
    //    @Cacheable(value = "miniData",key = "#dggObject.getString('pname')+'-' +#dggObject.getString('h')")
    public Map anMinView(Map peakAndAmountData, List<Map> viewData, Map costData, Map avgData) {
        //        第一个视图
        List<Map> v1 = new LinkedList<>();
        if (peakAndAmountData != null) {
            if (peakAndAmountData.containsKey("10:00")) {
                Map temp = new HashMap();
                temp.put("timeSt", "10:00");
                temp.put("type", "接通量");
                Double t10 = Double.parseDouble(peakAndAmountData.get("10:00").toString());
                temp.put("nowjtl", t10 >= 0 ? t10 : 0);
                v1.add(temp);
            }

            if (peakAndAmountData.containsKey("11:00")) {
                Map temp = new HashMap();
                temp.put("timeSt", "11:00");
                temp.put("type", "接通量");
                Double t11 = Double.parseDouble(peakAndAmountData.get("11:00").toString());
                temp.put("nowjtl", t11 >= 0 ? t11 : 0);
                v1.add(temp);
            }

            if (peakAndAmountData.containsKey("12:00")) {
                Map temp = new HashMap();
                temp.put("timeSt", "12:00");
                temp.put("type", "接通量");
                Double t12 = Double.parseDouble(peakAndAmountData.get("12:00").toString());
                temp.put("nowjtl", t12 >= 0 ? t12 : 0);
                v1.add(temp);
            }

            if (peakAndAmountData.containsKey("15:00")) {
                Map temp = new HashMap();
                temp.put("timeSt", "15:00");
                temp.put("type", "接通量");
                Double t15 = Double.parseDouble(peakAndAmountData.get("15:00").toString());
                temp.put("nowjtl", t15 >= 0 ? t15 : 0);
                v1.add(temp);
            }

            if (peakAndAmountData.containsKey("16:00")) {
                Map temp = new HashMap();
                temp.put("timeSt", "16:00");
                temp.put("type", "接通量");
                Double t16 = Double.parseDouble(peakAndAmountData.get("16:00").toString());
                temp.put("nowjtl", t16 >= 0 ? t16 : 0);
                v1.add(temp);
            }

            if (peakAndAmountData.containsKey("17:00")) {
                Map temp = new HashMap();
                temp.put("timeSt", "17:00");
                temp.put("type", "接通量");
                Double t17 = Double.parseDouble(peakAndAmountData.get("17:00").toString());
                temp.put("nowjtl", t17 >= 0 ? t17 : 0);
                v1.add(temp);
            }

            if (peakAndAmountData.containsKey("18:00")) {
                Map temp = new HashMap();
                temp.put("timeSt", "18:00");
                temp.put("type", "接通量");
                Double t18 = Double.parseDouble(peakAndAmountData.get("18:00").toString());
                temp.put("nowjtl", t18 >= 0 ? t18 : 0);
                v1.add(temp);
            }
            if (peakAndAmountData.containsKey("20:00")) {
                Map temp = new HashMap();
                temp.put("timeSt", "20:00");
                temp.put("type", "接通量");
                Double t20 = Double.parseDouble(peakAndAmountData.get("20:00").toString());
                temp.put("nowjtl", t20 >= 0 ? t20 : 0);
                v1.add(temp);
            }
        }

//        其他视图的数据
        List<Map> v2 = new LinkedList<>();
//        30s、60s的相关数据
        List<Map> v3 = new LinkedList<>();
//        通话时长的相关数据
        List<Map> v4 = new LinkedList<>();

//        总费用
        double costAmount = 0;
//        v2-v4视图的数据
        for (Map map : viewData) {
//            电话量临时数据
            Map dhl = new HashMap();
//            接通量临时数据
            Map jtl = new HashMap();

//            30s电话量临时数据
            Map s30 = new HashMap();
//            60s电话量临时数据
            Map s60 = new HashMap();

//          通话时长数据
            Map thTime = new HashMap();

            dhl.put("type", "电话量");
            dhl.put("depart", map.get("sname"));
            dhl.put("amount", map.get("phoneAmount"));

            jtl.put("type", "接通量");
            jtl.put("depart", map.get("sname"));
            jtl.put("amount", map.get("phoneDone"));
//            添加费用信息
//            低于60秒的通话量，按照一分钟计算话费
            double costFix = Double.parseDouble(map.get("costFix").toString());
            int up60s = (int) map.get("amount60");
            int down60s = (int) map.get("phoneDone") - up60s;
            double oneCost = costFix * (up60s * 4 + down60s);
            jtl.put("count", oneCost);
            costAmount += oneCost;

            s30.put("sec", "30s");
            s30.put("dep", map.get("sname"));
            s30.put("num", map.get("amount30"));

            s60.put("sec", "60s");
            s60.put("dep", map.get("sname"));
            s60.put("num", map.get("amount60"));

            thTime.put("depart", map.get("sname"));
            thTime.put("type", "时长");
            thTime.put("timeamount", map.get("phoneTime"));


            v2.add(dhl);
            v2.add(jtl);

            v3.add(s30);
            v3.add(s60);

            v4.add(thTime);

        }
        costData.put("costAmount", costAmount);

        Map otherDate = new HashMap();
        if (peakAndAmountData != null) {
            String v1_1 = peakAndAmountData.get("jietongliang") + "/" + peakAndAmountData.get("totalJtl");
            otherDate.put("v1_1", v1_1);
            Double v1_2 = Double.parseDouble(avgData.get("pdavg").toString());
            otherDate.put("v1_2", v1_2);
            Double v1_3 = Double.parseDouble(peakAndAmountData.get("jietongliang").toString()) / Double.parseDouble(avgData.get("pdavg").toString());
            otherDate.put("v1_3", v1_3 * 100);
            Double v2_1 = Double.parseDouble(peakAndAmountData.get("jietongliang").toString()) / Double.parseDouble(peakAndAmountData.get("totalJtl").toString());
            otherDate.put("v2_1", v2_1 * 100);
            Double v2_2 = Double.parseDouble(avgData.get("jtl").toString());
            otherDate.put("v2_2", v2_2 * 100);
            Double v2_3 = v2_1 / v2_2;
            otherDate.put("v2_3", v2_3 * 100);
            String v3_1 = peakAndAmountData.get("amount30") + "/" + peakAndAmountData.get("amount60");
            otherDate.put("v3_1", v3_1);
            String v3_2 = avgData.get("amount30").toString().substring(0, avgData.get("amount30").toString().indexOf(".")) + "/" + avgData.get("amount60").toString().substring(0, avgData.get("amount60").toString().indexOf("."));
            otherDate.put("v3_2", v3_2);
            Double v3_3 = Double.parseDouble(peakAndAmountData.get("amount30").toString()) / Double.parseDouble(avgData.get("amount30").toString());
            Double v3_4 = Double.parseDouble(peakAndAmountData.get("amount60").toString()) / Double.parseDouble(avgData.get("amount60").toString());
            otherDate.put("v3_3", v3_3 * 100);
            otherDate.put("v3_4", v3_4 * 100);
            Double v4_1 = Double.parseDouble(peakAndAmountData.get("phoneTime").toString());
            otherDate.put("v4_1", v4_1);
            Double v4_2 = Double.parseDouble(avgData.get("ptavg").toString());
            otherDate.put("v4_2", v4_2);
            Double v4_3 = v4_1 / v4_2;
            otherDate.put("v4_3", v4_3 * 100);
            Double v5_1 = Double.parseDouble(costData.get("costAmount").toString());
            otherDate.put("v5_1", v5_1);
            Double v5_2 = Double.parseDouble(costData.get("costFix").toString());
            otherDate.put("v5_2", v5_2);
            Double v5_3 = Double.parseDouble(avgData.get("costAvg").toString());
            otherDate.put("v5_3", v5_3);
            Double v5_4 = v5_3 == 0 ? 0 : v5_1 / v5_3;
            otherDate.put("v5_4", v5_4 * 100);
            int v5_5 = (int) costData.get("isCost");
            otherDate.put("v5_5", v5_5);

        }

        Map result = new HashMap();
        result.put("peakAndAmountData", peakAndAmountData);
        result.put("v1", v1);
        result.put("v2", v2);
        result.put("v3", v3);
        result.put("v4", v4);
        result.put("costData", costData);
        result.put("otherDate", otherDate);
        return result;
    }

    //    大图分析
//    @Cacheable(value = "miniData",key = "#dggObject.getString('pname')+'-' + #dggObject.getString('viewDate')+'-'  +#dggObject.getString('h')")
    public Map anBigView(List<Map> viewData) {
        Map titleData = new HashMap();
        List leftViewData = new LinkedList();
        List rightViewData = new LinkedList();
        List leftdwViewData = new LinkedList();
        List timeViewData = new LinkedList();
        List moneyViewData = new LinkedList();

        Double phoneAmount = 0d;
        Double phoneDone = 0d;
        int amount30s = 0;
        int amount60s = 0;
        int timeAmount = 0;
        Double moneyAmount = 0d;

        for (Map map : viewData) {
            phoneAmount += Double.parseDouble(map.get("phoneAmount").toString());
            phoneDone += Double.parseDouble(map.get("phoneDone").toString());

            if (map.containsKey("time10")) {
//                第一个大图数据
                Map temp = new HashMap();
                temp.put("name", map.get("sname"));
                Double t10 = Double.parseDouble(map.get("time10").toString());
                temp.put("接通量", t10 >= 0 ? t10 : 0);
                temp.put("时间", "10:00");
                leftViewData.add(temp);

            }
            if (map.containsKey("time11")) {
                Map temp = new HashMap();
                temp.put("name", map.get("sname"));
                Double t11 = Double.parseDouble(map.get("time11").toString());
                temp.put("接通量", t11 >= 0 ? t11 : 0);
                temp.put("时间", "11:00");
                leftViewData.add(temp);
            }
            if (map.containsKey("time12")) {
                Map temp = new HashMap();
                temp.put("name", map.get("sname"));
                Double t12 = Double.parseDouble(map.get("time12").toString());
                temp.put("接通量", t12 >= 0 ? t12 : 0);
                temp.put("时间", "12:00");
                leftViewData.add(temp);
            }
            if (map.containsKey("time15")) {
                Map temp = new HashMap();
                temp.put("name", map.get("sname"));
                Double t15 = Double.parseDouble(map.get("time15").toString());
                temp.put("接通量", t15 >= 0 ? t15 : 0);
                temp.put("时间", "15:00");
                leftViewData.add(temp);
            }
            if (map.containsKey("time16")) {
                Map temp = new HashMap();
                temp.put("name", map.get("sname"));
                Double t16 = Double.parseDouble(map.get("time16").toString());
                temp.put("接通量", t16 >= 0 ? t16 : 0);
                temp.put("时间", "16:00");
                leftViewData.add(temp);
            }
            if (map.containsKey("time17")) {
                Map temp = new HashMap();
                temp.put("name", map.get("sname"));
                Double t17 = Double.parseDouble(map.get("time17").toString());
                temp.put("接通量", t17 >= 0 ? t17 : 0);
                temp.put("时间", "17:00");
                leftViewData.add(temp);
            }
            if (map.containsKey("time18")) {
                Map temp = new HashMap();
                temp.put("name", map.get("sname"));
                Double t18 = Double.parseDouble(map.get("time18").toString());
                temp.put("接通量", t18 >= 0 ? t18 : 0);
                temp.put("时间", "18:00");
                leftViewData.add(temp);
            }
            if (map.containsKey("time20")) {
                Map temp = new HashMap();
                temp.put("name", map.get("sname"));
                Double t20 = Double.parseDouble(map.get("time20").toString());
                temp.put("接通量", t20 >= 0 ? t20 : 0);
                temp.put("时间", "20:00");
                leftViewData.add(temp);
            }

            //                第二个大图数据
            Map temp02 = new HashMap();
            temp02.put("question", map.get("sname"));
            Double percent01 = Double.parseDouble(map.get("phoneDone").toString()) / Double.parseDouble(map.get("phoneAmount").toString());
            Double percent = percent01 * 2;

            BigDecimal bp01 = new BigDecimal(percent01);
            BigDecimal bp = new BigDecimal(percent);

            double f1 = bp01.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            double f = bp.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

            temp02.put("percent01", f1);
            temp02.put("percent", f);
            rightViewData.add(temp02);


//            第三个大图数据
            Map temps30 = new HashMap();
            temps30.put("country", map.get("sname"));
            temps30.put("type", "30s接通量");
            temps30.put("value", map.get("amount30"));
            amount30s += (int) map.get("amount30");

            Map temps60 = new HashMap();
            temps60.put("country", map.get("sname"));
            temps60.put("type", "60s接通量");
            temps60.put("value", map.get("amount60"));
            amount60s += (int) map.get("amount60");

            leftdwViewData.add(temps30);
            leftdwViewData.add(temps60);

//            第四个大图数据
            Map timeTemp = new HashMap();
            timeTemp.put("item", map.get("sname"));
            timeTemp.put("count", map.get("phoneTime"));
            timeAmount += (int) map.get("phoneTime");
            timeViewData.add(timeTemp);

//            第五个大图的数据
            Map moneyTemp = new HashMap();
            Double costFix = Double.parseDouble(map.get("costFix").toString());
            Double minute60s = Double.parseDouble(map.get("amount60").toString());
            Double minutAmount = Double.parseDouble(map.get("phoneDone").toString()) + 3 * minute60s;

            BigDecimal ma = new BigDecimal(minutAmount * costFix);
            Double mt = ma.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            moneyAmount += mt;

            moneyTemp.put("items", map.get("sname"));
            moneyTemp.put("count", mt);
            moneyViewData.add(moneyTemp);
        }

//        添加标题数据
        Double jtlv = 0d;
        if (phoneAmount != 0) {
            BigDecimal titlejtl = new BigDecimal(phoneDone / phoneAmount);
            jtlv = titlejtl.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        } else {
            jtlv = 0d;
        }
        titleData.put("phoneAmount", phoneAmount);
        titleData.put("phoneDone", phoneDone);
        titleData.put("jtlv", jtlv);
        titleData.put("amount30s", amount30s);
        titleData.put("amount60s", amount60s);
        titleData.put("timeAmount", timeAmount);
        BigDecimal mm = new BigDecimal(moneyAmount);
        Double mtemp = mm.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

        titleData.put("moneyAmount", mtemp);

        Map result = new HashMap();
        result.put("leftData", leftViewData);
        result.put("rightData", rightViewData);
        result.put("leftdwData", leftdwViewData);
        result.put("timeViewData", timeViewData);
        result.put("moneyViewData", moneyViewData);

        result.put("titleData", titleData);
        return result;
    }

}
