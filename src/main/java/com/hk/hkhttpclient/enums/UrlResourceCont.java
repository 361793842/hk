package com.hk.hkhttpclient.enums;

import lombok.Getter;

/**
 * @author : muwei
 * @ClassName:UrlResourceCont
 * @Date: 2020/4/9 09:36
 * @Description: TODO
 */
@Getter
public enum  UrlResourceCont {
    //根区域信息
    API_ZONE_ROOT("API_ZONE","/api/resource/v1/regions/root"),
    //查询区域列表v2
    ZONE_LEVEL_TWO("ZONE_LEVEL_TWO","/api/irds/v2/region/nodesByParams"),
    //根据区域编号获取下一级区域列表v2
    ZONE_LEVEL_THREE("ZONE_LEVEL_THREE","/api/resource/v2/regions/subRegions"),
    //获取资源列表v2
    RESOURCES("RESOURCES","/api/irds/v2/deviceResource/resources"),
    //查询入侵报警主机列表
    IASDEVICE_LIST("IASDEVICE_LIST","/api/resource/v1/iasDevice/search"),
    //获取入侵报警主机通道列表
    IASCHANNEL_LIST("IASCHANNEL_LIST","/api/resource/v1/iasChannel/get"),
    //入侵报警事件日志查询v2
    ALARM_LOG("ALARM_LOG","/api/scpms/v1/eventLogs/searches"),
    //查询事件订阅信息
    SERACH_EVENT("SERACH_EVENT","/api/eventService/v1/eventSubscriptionView"),
    //按事件类型订阅事件
    ADD_EVENT("ADD_EVENT","/api/eventService/v1/eventSubscriptionByEventTypes"),
    //取消事件
    DEL_EVENT("DEL_EVENT","/api/eventService/v1/eventUnSubscriptionByEventTypes"),
    API_ZONE("API_ZONE","/api/resource/v1/regions"),
    API_ZONE_ADD("API_ZONE_ADD","/api/resource/v1/region/batch/add"),
    API_ZONE_UPDATE("API_ZONE_UPDATE","/api/resource/v1/region/single/update"),
    API_EQUIPMENT_NET("API_EQUIPMENT_NET","/api/nms/v1/online/history_status"),
    ALARM_INFO("ALARM_INFO","/api"),
    //资源列表v2
    SOURCE_LIST_V2("SOURCE_LIST_V2","/api/irds/v2/resource/resourcesByParams")

    ;
    private String code;
    private String msg;

    UrlResourceCont(String code,String msg){
        this.code=code;
        this.msg=msg;
    }
}
