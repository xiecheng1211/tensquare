package com.tensquare.user.handler;

import entity.Result;
import entity.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author dongcheng
 * create date 2019/2/27
 **/
@ControllerAdvice
public class UserHandler {

    @ExceptionHandler(Exception.class)
    public Result Exexption(Exception e){
        return new Result(true, StatusCode.ERROR, e.getMessage());
    }

}
