package com.wuhanyunzhong.itass.controller;

import com.alibaba.excel.EasyExcel;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.wuhanyunzhong.itass.listener.DemoDataListener;
import com.wuhanyunzhong.itass.listener.HourDataListener;
import com.wuhanyunzhong.itass.service.DggService;
import com.wuhanyunzhong.itass.util.DepartDate;
import com.wuhanyunzhong.itass.util.JsonResult;
import com.wuhanyunzhong.itass.util.JtlDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("test")
public class testController {

    public static List<Map> allDepart = null;
    public static List<JtlDate> jtlDates = null;
    public static List<DepartDate> firstData = null;

    public static String sessionId = "";

    PasswordEncoder bp = new BCryptPasswordEncoder();

    @Autowired
    DggService dggService;


    @GetMapping("tdata")
    public JsonResult test01(@RequestParam("formdata") String formdata){
        test01();
//        String filePath = "src/main/webapp/temporary/depart.xlsx";
//        EasyExcel.read(filePath, DepartDate.class, new DemoDataListener()).sheet().headRowNumber(0).doRead();
//        Integer integer = dggService.addFirst(firstData);
        return null;
    }

    void test01(){
//        String filePath = "src/main/webapp/temporary/jtl01.xlsx";
//        File file =  new File(filePath);
//        EasyExcel.read(filePath, JtlDate.class, new HourDataListener()).sheet().headRowNumber(3).doRead();
//        Integer integer = dggService.upData(jtlDates);


//     添加事业部信息
//         这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        String filePath = "src/main/webapp/temporary/depart.xlsx";
        EasyExcel.read(filePath, DepartDate.class, new DemoDataListener()).sheet().headRowNumber(0).doRead();
        Integer integer = dggService.insertDepart(allDepart);

    }

    void test02(){
        String name = "wuhan";
        String password = bp.encode("123");
        Map map = new HashMap();
        map.put("uname",name);
        map.put("password",password);
        dggService.addUser(map);
    }
}
