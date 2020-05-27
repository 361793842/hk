package com.hk.hkhttpclient.Tools;

import com.alibaba.fastjson.JSONObject;
import com.hikvision.artemis.sdk.ArtemisHttpUtil;
import com.hk.hkhttpclient.configure.HkArtemisConfig;
import com.hk.hkhttpclient.configure.ReadCnf;
import com.hk.hkhttpclient.constant.ErrCode;
import com.hk.hkhttpclient.enums.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : muwei
 * @ClassName:HkSdkUtil
 * @Date: 2020/4/9 13:26
 * @Description: TODO
 */
@Slf4j
public class HkSdkUtil {
    private  String ROOT = "/artemis";
    private static ReadCnf readCnf= SpringUtil.getBean(ReadCnf.class);
    public HkSdkUtil(){
        HkArtemisConfig.setConfig();
    }
    public  Map<String,String> getPath(String url){
        Map<String, String> path=new HashMap<>();
        path.put("https://", ROOT+url);
        return path;
    }
    //获取区域根信息
    public  String SearchZoneRoot(){
        //封装请求参数
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("treeCode", 0);
        String body = jsonBody.toJSONString();
        //调用接口
        String result = ArtemisHttpUtil.doPostStringArtemis(getPath(UrlResourceCont.API_ZONE_ROOT.getMsg()),
                        body, null,null, ContentType.JSON.getMsg(), null);
        return result;
    }
    //查询区域列表v2
    public  String SearchZoneLevelTwo(){
        //封装请求参数root000000
        String[] parentIndexCodes={"root000000"};
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("resourceType", "region");
        jsonBody.put("parentIndexCodes", parentIndexCodes);
        jsonBody.put("isSubRegion", true);
        jsonBody.put("pageNo", 1);
        jsonBody.put("pageSize", 100);
        String body = jsonBody.toJSONString();
        //调用接口
        String result = ArtemisHttpUtil.doPostStringArtemis(getPath(UrlResourceCont.ZONE_LEVEL_TWO.getMsg()),
                body, null,null, ContentType.JSON.getMsg(), null);
        return result;
    }
    //根据区域编号获取下一级区域列表v2
    public  String SearchZoneLevelThree(String parentIndexCode){
        //封装请求参数
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("parentIndexCode", parentIndexCode);
        jsonBody.put("resourceType", EqumentType.camera.getCode());
        jsonBody.put("pageNo", 1);
        jsonBody.put("pageSize", 100);
        String body = jsonBody.toJSONString();
        //调用接口
        String result = ArtemisHttpUtil.doPostStringArtemis(getPath(UrlResourceCont.ZONE_LEVEL_THREE.getMsg()),
                body, null,null, ContentType.JSON.getMsg(), null);
        return result;
    }
    //获取资源列表v2
    public  String SearchResources(){
        //封装请求参数
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("resourceType", EqumentType.camera.getCode());
        jsonBody.put("pageNo", 1);
        jsonBody.put("pageSize", 100);
        String body = jsonBody.toJSONString();
        //调用接口
        String result = ArtemisHttpUtil.doPostStringArtemis(getPath(UrlResourceCont.RESOURCES.getMsg()),
                body, null,null, ContentType.JSON.getMsg(), null);
        return result;
    }
    //查询入侵报警主机列表IASDEVICE_LIST
    public  String SearchIasDeviceList(){
        //封装请求参数
        String[] regionIndexCodes={"05a47ad3-0ab3-4ded-96e6-001e98ea5ad1"};
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("regionIndexCodes", regionIndexCodes);
        jsonBody.put("pageNo", 1);
        jsonBody.put("pageSize", 100);
        jsonBody.put("name", "");
        jsonBody.put("containSubRegion", true);
        jsonBody.put("fieldValueNotNull", "name");
        String body = jsonBody.toJSONString();
        //调用接口
        String result = ArtemisHttpUtil.doPostStringArtemis(getPath(UrlResourceCont.IASDEVICE_LIST.getMsg()),
                body, null,null, ContentType.JSON.getMsg(), null);
        return result;
    }
    //获取入侵报警主机通道列表
    public  String SearchIasChannelList(){
        //封装请求参数
        String[] regionIndexCodes={"05a47ad3-0ab3-4ded-96e6-001e98ea5ad1"};
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("resourceType", EqumentType.subSys.getCode());
        jsonBody.put("pageNo", 1);
        jsonBody.put("pageSize", 100);
        String body = jsonBody.toJSONString();
        //调用接口
        String result = ArtemisHttpUtil.doPostStringArtemis(getPath(UrlResourceCont.IASCHANNEL_LIST.getMsg()),
                body, null,null, ContentType.JSON.getMsg(), null);
        return result;
    }
    //入侵报警事件日志查询v2
    public  String SearchAlarmLog(){
        //封装请求参数
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("pageNo", 1);
        jsonBody.put("pageSize", 100);
       // jsonBody.put("resourceType", EqumentType.subSys);
        String body = jsonBody.toJSONString();
        String result="";
        //调用接口
        result = ArtemisHttpUtil.doPostStringArtemis(getPath(UrlResourceCont.ALARM_LOG.getMsg()),
                body, null,null, ContentType.JSON.getMsg(), null);

        return result;
    }
    //订阅事件
    public  String AddEvent(){
        //封装请求参数
        int[] eventTypes={
                Event.ALARM_CS_BF.getCode(),
                Event.ALARM_CS_CF.getCode(),
                Event.ALARM_CS_CANCEL.getCode(),
                Event.ALARM_SZ_PL.getCode(),
                Event.ALARM_SZ_CPL.getCode(),
                Event.ALARM_SZ_ALARM.getCode()};
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("eventTypes", eventTypes);
        jsonBody.put("eventDest","http://"+ readCnf.getEventRecvIp() +":"+readCnf.getServerPort()+"/eventRcv");
        jsonBody.put("subType",2);
        jsonBody.put("eventLvl",null);
        // jsonBody.put("resourceType", EqumentType.subSys);
        String body = jsonBody.toJSONString();
        //调用接口
        String result = ArtemisHttpUtil.doPostStringArtemis(getPath(UrlResourceCont.ADD_EVENT.getMsg()),
                body, null,null, ContentType.JSON.getMsg(), null);
        return result;
    }
    //查询事件
    public  String SearchEvent(){
        //封装请求参数
        JSONObject jsonBody = new JSONObject();
        String body = jsonBody.toJSONString();
        //调用接口
        String result = ArtemisHttpUtil.doPostStringArtemis(getPath(UrlResourceCont.SERACH_EVENT.getMsg()),
                body, null,null, ContentType.JSON.getMsg(), null);
        return result;
    }
    //删除事件
    public  String DelEvent( Object object){
        //封装请求参数
        //int[] eventTypes={EventType.ALARM.getCode()};
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("eventTypes",object);
        String body = jsonBody.toJSONString();
        //调用接口
        String result = ArtemisHttpUtil.doPostStringArtemis(getPath(UrlResourceCont.DEL_EVENT.getMsg()),
                body, null,null, ContentType.JSON.getMsg(), null);
        return result;
    }
    //获取区域信息
    public Map<String,Object> SearchZone(){
        //封装请求参数
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("pageNo", 1);
        jsonBody.put("pageSize", 1000);
        String body = jsonBody.toJSONString();
        //调用接口
        String result = ArtemisHttpUtil.doPostStringArtemis(getPath(UrlResourceCont.API_ZONE.getMsg()),
                body, null,null, ContentType.JSON.getMsg(), null);
        Map<String,Object> rs=GsonUtil.json2Object(result,Map.class);
        return rs;
    }
    /**
     * 功能描述:增加区域信息
     *         "clientId": 11,
     *         "parentIndexCode": "24tvfv3v5v389c8y54c808y5c",
     *         "regionName": "区域1",
     *         "regionCode": "3q8c8y820y",
     *         "regionType": 10,
     *         "description": "区域描述"
     * @param:
     * @return:
     * @auther: mw
     * @date: 2020/4/9 14:55
     */
    public  Map<String, Object> AddZone(){
        //封装请求参数
        List<Map<String,Object>> list=new ArrayList<>();
        Map<String,Object> map=new HashMap<>();
        map.put("clientId", 11);
        map.put("parentIndexCode", "root000000");
        map.put("regionName", "四川省");
        map.put("regionCode", "3q8c8y820y");
        map.put("regionType", 10);
        map.put("description", "四川省");
        list.add(map);
        String body = GsonUtil.object2Json(list);
        //调用接口
        String result = ArtemisHttpUtil.doPostStringArtemis(getPath(UrlResourceCont.API_ZONE_ADD.getMsg()),
                body, null,null, ContentType.JSON.getMsg(), null);
        Map<String,Object> rs=GsonUtil.json2Object(result,Map.class);
        return rs;
    }
    /**
     * 功能描述:更新区域信息
     * @param:
     * @return:
     * @auther: mw
     * @date: 2020/4/9 15:49
     */
    public  Map<String,Object> UpdateZone(){
        //封装请求参数
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("regionIndexCode", "100000");
        jsonBody.put("regionName", "全中国");
        String body = jsonBody.toJSONString();
        //调用接口
        String result = ArtemisHttpUtil.doPostStringArtemis(getPath(UrlResourceCont.API_ZONE_UPDATE.getMsg()),
                body, null,null, ContentType.JSON.getMsg(), null);
        Map<String,Object> rs=GsonUtil.json2Object(result,Map.class);
        return rs;
    }
    //获取设备网管在线记录
    public  String EquipmentNet(){
        //封装请求参数
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("indexCode", 1);
        jsonBody.put("startDate", 1);
        jsonBody.put("endDate", "");
        jsonBody.put("pageNo", 1);
        jsonBody.put("pageSize", 20);
        jsonBody.put("resourceType", "door");
        String body = jsonBody.toJSONString();
        //调用接口
        String result = ArtemisHttpUtil.doPostStringArtemis(getPath(UrlResourceCont.API_ZONE.getMsg()),
                body, null,null, ContentType.JSON.getMsg(), null);
        return result;
    }
    //查询资源信息
}
