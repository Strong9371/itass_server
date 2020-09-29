package com.wuhanyunzhong.itass.controller;

import com.alibaba.excel.EasyExcel;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.wuhanyunzhong.itass.listener.DemoDataListener;
import com.wuhanyunzhong.itass.service.DggService;
import com.wuhanyunzhong.itass.util.DepartDate;
import com.wuhanyunzhong.itass.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("test")
public class testController {

    public static List<Map> allDepart = null;
    public static String sessionId = "";

    @Autowired
    DggService dggService;

    @GetMapping("tdata")
    @ResponseBody
    public JsonResult test01(@RequestParam("formdata") String formdata){


        System.out.println(formdata);
        String filePath = "src/main/webapp/temporary/depart.xlsx";
        File file =  new File(filePath);
        System.err.println(file.length());

        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(filePath, DepartDate.class, new DemoDataListener()).sheet().headRowNumber(0).doRead();
//        Integer test = dggService.test();
//        Map setest = dggService.setest();
//        System.err.println(setest);
        System.err.println("kjk");
        System.err.println(allDepart);
        Integer integer = dggService.insertDepart(allDepart);
        System.err.println(integer);
        return null;
    }
}
