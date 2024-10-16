package com.taskflow.exception;

public class ExceptionTaskFlow extends Exception {
    public ExceptionTaskFlow(String msg){
        super(msg);
    }
    public ExceptionTaskFlow(String msg, Throwable cause){
        super(msg, cause);
    }
}
