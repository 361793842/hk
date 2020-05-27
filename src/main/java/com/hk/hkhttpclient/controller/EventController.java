package com.hk.hkhttpclient.controller;

import com.hk.hkhttpclient.Tools.GsonUtil;
import com.hk.hkhttpclient.Tools.HkSdkUtil;
import com.hk.hkhttpclient.enums.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
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
