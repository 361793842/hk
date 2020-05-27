package com.hk.hkhttpclient.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author : mw
 * @ClassName:Result
 * @Date: 2019/6/18 15:44
 * @Description: TODO
 */

@Getter
@Setter
@ToString
public class Result<T> {
    private int code;
    private String msg;
    private T data;
    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public Result() {
    }
}
