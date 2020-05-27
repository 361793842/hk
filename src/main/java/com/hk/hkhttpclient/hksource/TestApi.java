package com.hk.hkhttpclient.hksource;

import com.hk.hkhttpclient.Tools.GsonUtil;
import com.hk.hkhttpclient.Tools.HkSdkUtil;
import com.hk.hkhttpclient.Tools.ToolsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author : muwei
 * @ClassName:TestApi
 * @Date: 2020/4/9 13:48
 * @Description: TODO
 */
@Slf4j
public class TestApi {
    private static HkSdkUtil hkSdkUtil=new HkSdkUtil();
    public static void main(String[] args) {
        log.info("接收返回:{}", hkSdkUtil.SearchZoneRoot());
        log.info("区域列表2:{}", hkSdkUtil.SearchZoneLevelTwo());
        log.info("根据区域编号获取下一级区域列表v2:{}",
                hkSdkUtil.SearchZoneLevelThree("05a47ad3-0ab3-4ded-96e6-001e98ea5ad1"));
        log.info("资源列表:{}", hkSdkUtil.SearchResources());
        log.info("查询入侵报警主机列表:{}", hkSdkUtil.SearchIasDeviceList());
        log.info("获取入侵报警主机通道列表:{}", hkSdkUtil.SearchIasChannelList());
        log.info("入侵报警事件日志查询v2:{}", hkSdkUtil.SearchAlarmLog());

        Map<String,Object> rs= GsonUtil.json2Object(hkSdkUtil.SearchAlarmLog(),Map.class);
        if (rs.get("data")!=null){
            Map<String,Object> data= (Map<String, Object>) rs.get("data");
            List<Map<String,Object>> list= (List<Map<String, Object>>) data.get("list");
            ToolsUtil.printList(list);
        }


        log.info("订阅事件:{}", hkSdkUtil.AddEvent());
        log.info("查询事件:{}", hkSdkUtil.SearchEvent());
       // log.info("删除事件:{}", hkSdkUtil.DelEvent(list));

        //log.info("接收返回:{}", hkSdkUtil.SearchZone());
        //log.info("接收返回:{}", hkSdkUtil.AddZone());
       /* log.info("接收返回:{}", hkSdkUtil.SearchZone());
        log.info("接收返回:{}", hkSdkUtil.AddZone());
        log.info("接收返回:{}", hkSdkUtil.UpdateZone());*/
    }
}
