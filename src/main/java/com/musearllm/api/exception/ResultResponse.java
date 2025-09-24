package com.musearllm.api.exception;

/**
 * @description: 自定义数据传输
 * @author: DT
 * @date: 2021/4/19 21:47
 * @version: v1.0
 */
public class ResultResponse {

    private boolean success;
    /**
     * 响应代码
     */
    private int code;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 响应结果
     */
    private Object result;

    private Long timestamp;

    public ResultResponse() {
    }

    public ResultResponse(BaseErrorInfoInterface errorInfo) {
        this.success = false;
        this.code = errorInfo.getResultCode();
        this.message = errorInfo.getResultMsg();
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * 成功
     *
     * @return
     */
    public static ResultResponse success() {
        return success(null);
    }

    /**
     * 成功
     * @param data
     * @return
     */
    public static ResultResponse success(Object data) {
        ResultResponse rb = new ResultResponse();
        rb.setSuccess(true);
        rb.setCode(ExceptionEnum.SUCCESS.getResultCode());
        rb.setMessage(ExceptionEnum.SUCCESS.getResultMsg());
        rb.setResult(data);
        rb.setTimestamp(System.currentTimeMillis());
        return rb;
    }

    /**
     * 失败
     */
    public static ResultResponse error(BaseErrorInfoInterface errorInfo) {
        ResultResponse rb = new ResultResponse();
        rb.setSuccess(false);
        rb.setCode(errorInfo.getResultCode());
        rb.setMessage(errorInfo.getResultMsg());
        rb.setResult(null);
        rb.setTimestamp(System.currentTimeMillis());
        return rb;
    }


    /**
     * 失败
     */
    public static ResultResponse error(int code, String message) {
        ResultResponse rb = new ResultResponse();
        rb.setSuccess(false);
        rb.setCode(code);
        rb.setMessage(message);
        rb.setResult(null);
        rb.setTimestamp(System.currentTimeMillis());
        return rb;
    }

    /**
     * 失败
     */
    public static ResultResponse error(String message) {
        ResultResponse rb = new ResultResponse();
        rb.setSuccess(false);
        rb.setCode(-1);
        rb.setMessage(message);
        rb.setResult(null);
        rb.setTimestamp(System.currentTimeMillis());
        return rb;
    }

//    @Override
//    public String toString() {
//        return JSONObject.toJSONString(this);
//    }

}
