package com.hk.hkhttpclient.controller;

import com.hk.hkhttpclient.Tools.GsonUtil;
import com.hk.hkhttpclient.Tools.HkSdkUtil;
import com.hk.hkhttpclient.Tools.ResultUtil;
import com.hk.hkhttpclient.Tools.ToolsUtil;
import com.hk.hkhttpclient.bean.EventDetailBean;
import com.hk.hkhttpclient.bean.EventIasBean;
import com.hk.hkhttpclient.bean.LdEventBean;
import com.hk.hkhttpclient.bean.Result;
import com.hk.hkhttpclient.entity.WebLog;
import com.hk.hkhttpclient.enums.Event;
import com.hk.hkhttpclient.enums.EventType;
import com.hk.hkhttpclient.http.Request;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : muwei
 * @ClassName:EventController
 * @Date: 2020/5/15 16:13
 * @Description: TODO
 */
@RestController
@Slf4j
public class EventController {
    private static HkSdkUtil hkSdkUtil=new HkSdkUtil();
    @PostMapping("/eventRcv")
    public String log(@RequestBody Map<String,Object> map){
        log.info("订阅事件收到消息:{}",map);
        String method=map.get("method").toString().toLowerCase();
        if (method.equals("OnEventNotify".toLowerCase())){
            log.info("事件推送");
            Map<String,Object> params= (Map<String, Object>) map.get("params");
            if (params.get("ability").toString().equals(EventType.event_ias.getCode())){
                log.info(EventType.event_ias.getMsg());
                List<Map<String, Object>> events= (List<Map<String, Object>>) params.get("events");
                for(Map<String, Object> eventMap:events){
                    EventIasBean bean=new EventIasBean();
                    try {
                        BeanUtils.populate(bean,eventMap);
                        bean.setHappenTime(ToolsUtil.timeUTCFormat(bean.getHappenTime()));
                        log.info(bean.toString());
                        Request.pushAlarm(bean);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }else if (StringUtils.isEmpty(params.get("ability").toString())){
                log.info("event-detail");
                List<Map<String, Object>> events= (List<Map<String, Object>>) params.get("events");
                for (Map<String, Object> map1:events){
                    LdEventBean ldEventBean=new LdEventBean();
                    try {
                        BeanUtils.populate(ldEventBean,map1);
                        ldEventBean.setHappenTime(ToolsUtil.timeUTCFormat(ldEventBean.getHappenTime()));
                        log.info("events:{}",ldEventBean.toString());
                        List<Map<String,Object>> eventDetails= (List<Map<String, Object>>) map1.get("eventDetails");
                        for (Map<String,Object> map2:eventDetails){
                            EventDetailBean eventDetailBean=new EventDetailBean();
                            BeanUtils.populate(eventDetailBean,map2);
                            log.info("event-detail:{}",eventDetailBean.toString());
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return "HTTP/1.1 200 OK";
    }
    @PostMapping("/search")
    public Map<String,Object> search(){
        log.info("查询事件");
        Map<String,Object> map=new HashMap<>();
        map= GsonUtil.json2Object(hkSdkUtil.SearchEvent(),Map.class);
        return map;
    }
    @PostMapping("/del")
    public Map<String,Object> del(@RequestBody Map<String,Object> map){
        List<Integer> type= (List<Integer>) map.get("types");
        log.info("删除事件:{}",map);
        Map<String,Object> rmap=GsonUtil.json2Object(hkSdkUtil.DelEvent(type),Map.class);
        return rmap;
    }
    @PostMapping("/delAll")
    public Map<String,Object> delAll(){
        int[] eventTypes={
                Event.ALARM_CS_BF.getCode(),
                Event.ALARM_CS_CF.getCode(),
                Event.ALARM_CS_CANCEL.getCode(),
                Event.ALARM_SZ_PL.getCode(),
                Event.ALARM_SZ_CPL.getCode(),
                Event.ALARM_SZ_ALARM.getCode()};
        Map<String,Object> rmap=GsonUtil.json2Object(hkSdkUtil.DelEvent(eventTypes),Map.class);
        return rmap;
    }
    @PostMapping("/recv")
    public String recv(@RequestBody Map<String,Object> map){
        log.info("接收到推送事件:{}",map);
        return "success";
    }
}
