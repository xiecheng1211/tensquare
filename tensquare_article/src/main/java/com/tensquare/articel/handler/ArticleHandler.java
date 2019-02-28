package com.tensquare.articel.handler;

import entity.Result;
import entity.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author dongcheng
 * create date 2019/2/25
 **/
@ControllerAdvice
public class ArticleHandler {

    @ExceptionHandler(Exception.class)
    public Result execExecption(Exception e){
        return new Result(false, StatusCode.ERROR,e.getMessage()) ;
    }
}
