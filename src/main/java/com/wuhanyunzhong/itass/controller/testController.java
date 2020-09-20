package com.wuhanyunzhong.itass.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.wuhanyunzhong.itass.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("test")
public class testController {

    @GetMapping("tdata")
    @ResponseBody
    public JsonResult test01(@RequestParam("formdata") String formdata){


        System.out.println(formdata);
        return null;
    }
}
