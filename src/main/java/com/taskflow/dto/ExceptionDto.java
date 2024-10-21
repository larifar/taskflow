package com.taskflow.dto;

import org.springframework.http.HttpStatus;

public class ExceptionDto{
    private String msg;
    private String code;

    public ExceptionDto() {
    }

    public ExceptionDto(String msg, String code) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
