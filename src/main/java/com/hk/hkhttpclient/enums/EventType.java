package com.hk.hkhttpclient.enums;

import lombok.Getter;

/**
 * @author : muwei
 * @ClassName:EventType
 * @Date: 2020/5/15 15:56
 * @Description: TODO
 * 事件类别
 */
@Getter
public enum  EventType {
    event_ias("event_ias","标识入侵报警事件")
    ;
    private String code;
    private String msg;

    EventType(String code,String msg){
        this.code=code;
        this.msg=msg;
    }
}
