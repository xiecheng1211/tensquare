package com.tensquare.gather.handler;

import entity.Result;
import entity.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author dongcheng
 * create date 2019/2/26
 **/
@ControllerAdvice
public class GatherHandler {

    @ExceptionHandler(Exception.class)
    public Result execExcption(Exception e){
        return new Result(false, StatusCode.ERROR,e.getMessage());
    }

}
