package com.hk.hkhttpclient.enums;

import lombok.Getter;

/**
 * @author : muwei
 * @ClassName:ContentType
 * @Date: 2020/4/9 13:23
 * @Description: TODO
 */
@Getter
public enum  ContentType {
    JSON("JSON","application/json");
    private String code;
    private String msg;

    ContentType(String code,String msg){
        this.code=code;
        this.msg=msg;
    }
}
