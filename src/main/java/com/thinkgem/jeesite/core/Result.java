package com.thinkgem.jeesite.core;

public class Result {
    public int code = 0;
    public String message;

    public Result(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
