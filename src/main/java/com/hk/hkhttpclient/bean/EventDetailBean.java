package com.hk.hkhttpclient.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author : muwei
 * @ClassName:EventDetailBean
 * @Date: 2020/5/26 09:29
 * @Description: TODO
 * eventType	Number	事件类型	是	64
 * ability	String	事件类别	是	64	如视频事件、门禁事件，符合事件类型定义规范
 * srcIndex	String	事件源编号	是	64
 * srcType	String	事件源类型	是	64	事件联动固定为事件规则：eventRule
 * srcName	String	事件源名称	是	64	utf8,透传，应用自定义
 * regionIndexCode	String	区域编号	否	64
 * regionName	String	区域名称	否	64
 * locationIndexCode	String	位置编号	否	64
 * locationName	String	位置名称	否	64
 * data	Object	事件其它扩展信息	否	4096
 */
@Getter
@Setter
@ToString
public class EventDetailBean {
    private int eventType;
    private String data;
    private String eventOriginalId;
    private String ability;
    private String srcIndex;
    private String srcType;
    private String srcName;
    private String regionIndexCode;
    private String regionName;
    private String locationIndexCode;
    private String locationName;
   /* private String eventId;
    private int status;
    private int timeout;
    private String happenTime;
    private String srcParentIndex;*/
}
