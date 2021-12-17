package com.xinhua.utils;


import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//作为springmvc的异常处理器
@RestControllerAdvice
public class ProjectExceptionAdvice {
    //拦截所有的异常
    @ExceptionHandler(Exception.class)
    public R doException(Exception e) {
        //异常打印到控制台
        e.printStackTrace();
        //告诉前端
        return new R("服务器错误");
    }

    //不同异常不同处理
    @ExceptionHandler(ArithmeticException.class)
    public R doException1(Exception e) {
        //异常打印到控制台
        e.printStackTrace();
        //告诉前端
        return new R("1不能除0");
    }
}
