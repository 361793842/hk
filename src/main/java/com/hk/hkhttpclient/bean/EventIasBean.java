package com.hk.hkhttpclient.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author : muwei
 * @ClassName:EventIasBean
 * @Date: 2020/5/25 16:09
 * @Description: TODO
 * data	String	事件详情	否		实际返回与设备驱动有关，不与“报警 布防 撤防 旁路 旁路恢复 消警”完全对应，建议不做解析。若第三方驱动中没有此字段，则事件报文中也没有此字段。
 * eventId	String	事件唯一标识	是
 * srcIndex	String	事件源编号，物理设备是资源编号	是
 * srcType	String	事件源类型	是		defence-防区
 * subSys-子系统
 * srcName	String	事件源名称	否
 * eventType	Number	事件类型	是
 * status	Number	事件状态	是		0-瞬时
 * 1-开始
 * 2-停止
 * 3-事件脉冲
 * 4-事件联动结果更新
 * timeout	Number	脉冲超时时间	是		单位:秒
 * happenTime	String	事件发生时间（设备时间）	是
 * srcParentIndex	String	事件发生的事件源父设备编号	否
 */
@Getter
@Setter
@ToString
public class EventIasBean {
    private String data;
    private String eventId;
    private String srcIndex;
    private String srcType;
    private String srcName;
    private int eventType;
    private int status;
    private int timeout;
    private String happenTime;
    private String srcParentIndex;

}
