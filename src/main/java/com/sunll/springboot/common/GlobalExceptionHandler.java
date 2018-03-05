package com.sunll.springboot.common;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.Set;

/**
 * 全局异常捕获处理
 * Created by sunll
 * on 2018/3/1
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    /**
     * 所有异常报错
     *
     * @param exception
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = Exception.class)
    public String allExceptionHandler(Exception exception) throws Exception {
        exception.printStackTrace();
        System.out.println("我报错了：" + exception.getLocalizedMessage());
        System.out.println("我报错了：" + exception.getCause());
        System.out.println("我报错了：" + Arrays.toString(exception.getSuppressed()));
        System.out.println("我报错了：" + exception.getMessage());
        System.out.println("我报错了：" + Arrays.toString(exception.getStackTrace()));
        return "服务器异常，请联系管理员！";
    }

    /**
     * GET参数校验异常捕获
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handle(ConstraintViolationException exception) {
        Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
        for (ConstraintViolation<?> item : violations) {
            /**打印验证不通过的信息*/
            System.out.println(item.getMessage());
        }
        return "bad request";
    }

    /**
     * 添加全局异常处理流程，根据需要设置需要处理的异常，本文以MethodArgumentNotValidException为例
     * 数据实体验证捕获异常
     * @param exception
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public String MethodArgumentNotValidHandler(
            MethodArgumentNotValidException exception) {
        //解析原错误信息，封装后返回，此处返回非法的字段名称，原始值，错误信息
        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
            /**打印验证不通过的信息*/
            System.out.println(error.getDefaultMessage());
            System.out.println(error.getField());
            System.out.println(error.getRejectedValue());
        }
        return "bad request";
    }
}
