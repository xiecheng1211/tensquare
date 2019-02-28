package com.tensquare.qa.Handler;

import entity.Result;
import entity.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author dongcheng
 * create date 2019/2/24
 **/
@ControllerAdvice
public class QaHandler {

    @ExceptionHandler(value = Exception.class)
    public Result execException(Exception e){
        return new Result(false, StatusCode.ERROR,e.getMessage());
    }

}
