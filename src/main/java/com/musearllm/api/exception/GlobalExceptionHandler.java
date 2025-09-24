package com.musearllm.api.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.binding.BindingException;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.NoSuchFileException;

/**
 * @description: 自定义异常处理
 * @author: DT
 * @date: 2021/4/19 21:51
 * @version: v1.0
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理自定义的业务异常
     * 
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public ResultResponse bizExceptionHandler(HttpServletRequest req, BizException e) {
        log.error("business exception！cause：", e.getErrorMsg());
        // final LocalDateTime now = DateTimeUtil.getNow();
        return ResultResponse.error(e.getErrorCode(), e.getErrorMsg());
    }

    /**
     * 处理空指针的异常
     * 
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public ResultResponse exceptionHandler(HttpServletRequest req, NullPointerException e) {
        ExceptionEnum exceptionEnum = ExceptionEnum.NULL_POINTER;
        log.error("null point exception！cause:", e);
        return ResultResponse.error(exceptionEnum);
    }
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseBody
    public ResultResponse handleHttpMessageNotReadableException(HttpServletRequest req, HttpMessageNotReadableException e) {
        ExceptionEnum exceptionEnum = ExceptionEnum.BODY_NOT_MATCH;  // You can define your own exception enum for this
        log.error("HttpMessageNotReadableException: Invalid request body! Cause: ", e);
        return ResultResponse.error(exceptionEnum);
    }

    @ExceptionHandler(value = IOException.class)
    @ResponseBody
    public ResultResponse exceptionHandler(HttpServletRequest req, IOException e) {
        ExceptionEnum exceptionEnum = ExceptionEnum.IO_EXCEPTION;
        log.error("null point exception！cause:", e);
        return ResultResponse.error(exceptionEnum);
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    @ResponseBody
    public ResultResponse exceptionHandler(HttpServletRequest req, DataIntegrityViolationException e) {
        ExceptionEnum exceptionEnum = ExceptionEnum.DATA_TRUNCATION;
        log.error("Data truncation！cause:", e);
        return ResultResponse.error(exceptionEnum);
    }

    @ExceptionHandler(value = BindingException.class)
    @ResponseBody
    public ResultResponse exceptionHandler(HttpServletRequest req, BindingException e) {
        ExceptionEnum exceptionEnum = ExceptionEnum.BINDING_EXCEPTION;
        log.error("Invalid bound statement:", e);
        return ResultResponse.error(exceptionEnum);
    }

    @ExceptionHandler(value = UnsupportedOperationException.class)
    @ResponseBody
    public ResultResponse exceptionHandler(HttpServletRequest req, UnsupportedOperationException e) {
        ExceptionEnum exceptionEnum = ExceptionEnum.UNSUPPORTED_OPERATION_EXCEPTION;
        log.error("Unsupported Operation Exception:", e);
        return ResultResponse.error(exceptionEnum);
    }

    @ExceptionHandler(value = MyBatisSystemException.class)
    @ResponseBody
    public ResultResponse exceptionHandler(HttpServletRequest req, MyBatisSystemException e) {
        ExceptionEnum exceptionEnum = ExceptionEnum.DB_EXCEPTION;
        log.error("MyBatisSystemException:", e);
        return ResultResponse.error(exceptionEnum);
    }

    @ExceptionHandler(value = ArrayIndexOutOfBoundsException.class)
    @ResponseBody
    public ResultResponse exceptionHandler(HttpServletRequest req, ArrayIndexOutOfBoundsException e) {
        ExceptionEnum exceptionEnum = ExceptionEnum.ARRAY_INDEX_OUT_OF_BOUND_EXCEPTION;
        log.error("Array Index Out Of Bounds Exception:", e);
        return ResultResponse.error(exceptionEnum);
    }

    @ExceptionHandler(value = FileNotFoundException.class)
    @ResponseBody
    public ResultResponse exceptionHandler(HttpServletRequest req, FileNotFoundException e) {
        ExceptionEnum exceptionEnum = ExceptionEnum.FILE_NOT_FOUND_EXCEPTION;
        log.error("File Not Found Exception:", e);
        return ResultResponse.error(exceptionEnum);
    }

    @ExceptionHandler(value = BadSqlGrammarException.class)
    @ResponseBody
    public ResultResponse exceptionHandler(HttpServletRequest req, BadSqlGrammarException e) {
        ExceptionEnum exceptionEnum = ExceptionEnum.DB_EXCEPTION;
        log.error("Error in SQL syntax:", e);
        return ResultResponse.error(exceptionEnum);
    }

    @ExceptionHandler(value = NoSuchFileException.class)
    @ResponseBody
    public ResultResponse exceptionHandler(HttpServletRequest req, NoSuchFileException e) {
        ExceptionEnum exceptionEnum = ExceptionEnum.IO_EXCEPTION;
        log.error("No such file exception:", e);
        return ResultResponse.error(exceptionEnum);
    }

    // @ExceptionHandler(value = MethodArgumentNotValidException.class)
    // @ResponseBody
    // public ResultResponse exceptionHandler(HttpServletRequest req,
    // MethodArgumentNotValidException e){
    // log.error("File Not Found Exception:",e);
    // return
    // ResultResponse.error(ExceptionEnum.METHOD_ARGUMENT_NOT_VALID_EXCEPTION);
    // }

    /**
     * 处理其他异常
     * 
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultResponse exceptionHandler(HttpServletRequest req, Exception e) {
        ExceptionEnum exceptionEnum = ExceptionEnum.INTERNAL_SERVER_ERROR;
        log.error("unknown exception！cause:", e);
        return ResultResponse.error(exceptionEnum);
    }

    private void insertExceptionToDB() {

    }

}
