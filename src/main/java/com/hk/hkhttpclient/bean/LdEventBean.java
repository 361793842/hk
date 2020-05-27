package com.hk.hkhttpclient.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author : muwei
 * @ClassName:LdEventBean
 * @Date: 2020/5/26 09:47
 * @Description: TODO
 * eventId	String	事件唯一标识	是	64	同一事件若上报多次，则上报事件的eventId相同
 * srcIndex	String	事件源编号，物理设备是资源编号	是	64
 * srcType	String	事件源类型	是	64
 * srcName	String	事件源名称	否	64	utf8,透传，应用自定义
 * eventType	Number	事件类型	是	64
 * status	Number	事件状态	是	1	0-瞬时
 * 1-开始
 * 2-停止
 * 4-事件联动结果更新
 * 5-事件图片异步上传
 * timeout	Number	脉冲超时时间	是		单位：秒
 * eventName	string	事件规则名称	是	64
 * status	Number	事件状态	是	1	0-瞬时
 * 1-开始
 * 2-停止
 * 3-事件脉冲
 * 4-联动结果更新
 * 8-注释
 * happenTime	string	事件发生时间（设备时间）	是	64	ISO8601，示例：2018-08-15T 15:53:47.000+08:00
 * stopTime	string	事件结束时间	是	64
 * remark	string	用户自定义注释	是	64
 * eventDetails	EventDetail[]	事件详情	是	不限	参考eventDetails字段说明
 * linkageAcion	LinkageAcion[]	联动动作	否	不限	参考LinkageAcion字段说明
 * linkageResult	LinkageResult[]	联动结果更新	否	不限	参考LinkageResult字段说明
 */
@Setter
@Getter
@ToString
public class LdEventBean {
    private String ability;
    private String eventId;
    private int eventLvl;
    private String eventName;
    private String eventOldId;
    private int eventType;
    private String happenTime;
    private String remark;
    private String ruleDescription;
    private String srcIndex;
    private String srcType;
    private String srcName;
    private int status;
    private int timeout;
    private String srcParentIndex;
    private List<EventDetailBean> eventDetail;
}
