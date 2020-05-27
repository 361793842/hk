package com.hk.hkhttpclient.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author : muwei
 * @ClassName:ZoneBean
 * @Date: 2020/5/15 13:46
 * @Description: TODO
 */
@Getter
@Setter
@ToString
public class ZoneBean {
    private String indexCode;
    private String regionCode;
    private String name;
    private String parentIndexCode;
    private String catalogType;
    private String description;
    private int sort;
    private String regionPath;
    private boolean available;
    private int cascadeCode;
    private int cascadeType;
    private boolean leaf;
    private String createTime;
    private String updateTime;
}
