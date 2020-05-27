package com.hk.hkhttpclient.bean;

import lombok.Getter;
import lombok.Setter;

/**
 * @author : muwei
 * @ClassName:AppQuartz
 * @Date: 2020/5/22 14:00
 * @Description: TODO
 */
@Getter
@Setter
public class AppQuartz {
    private Integer quartzId;  //id  主键
    private String jobName;  //任务名称
    private String jobGroup;  //任务分组
    private String startTime;  //任务开始时间
    private String cronExpression;  //corn表达式
    private String invokeParam;//需要传递的参数
}
