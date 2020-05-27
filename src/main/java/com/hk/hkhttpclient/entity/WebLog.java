package com.hk.hkhttpclient.entity;

import lombok.Data;

/**
 * @author : muwei
 * @ClassName:WebLog
 * @Date: 2020/5/13 14:22
 * @Description: TODO
 */
@Data
public class WebLog {
    private int id;
    private String operator;
    private String content;
    private String time;
    private int type;
    private String ip;
}
