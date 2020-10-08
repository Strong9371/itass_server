package com.wuhanyunzhong.itass.controller;


import com.wuhanyunzhong.itass.service.exception.ServiceException;
import com.wuhanyunzhong.itass.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Strong
 * @create 2019-09-19-19:34
 */
public abstract class BaseController {

    protected static final Integer SUCCESS=1;

    //登录失败
    protected static final Integer ERROR=-1;





    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public JsonResult<Void> handleException(Throwable e){
        JsonResult<Void> jr=new JsonResult();

        if(e instanceof ServiceException){
            jr.setState(ERROR);
            jr.setMessage(e.getMessage());
        }else{
            jr.setState(ERROR);
            jr.setMessage("请稍后再试！");
        }
        return jr;
    }
}
