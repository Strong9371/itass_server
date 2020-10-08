package com.wuhanyunzhong.itass.util;

public class JsonResult<T> {
    private Integer state;
    private String message;
    private T data;

    public JsonResult() {
    }

    public JsonResult(Integer state, String message, T data) {
        this.state = state;
        this.message = message;
        this.data = data;
    }

    public JsonResult(Integer state) {
        this.state = state;
    }

    public JsonResult(String message) {
        this.message = message;
    }

    public JsonResult(T data) {
        this.data = data;
    }

    public Integer getState() {
        return state;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "JsonResult{" +
                "state=" + state +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
