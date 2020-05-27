package com.hk.hkhttpclient.controller;

import com.hk.hkhttpclient.entity.WebLog;
import com.hk.hkhttpclient.service.impl.WebLogSimpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : muwei
 * @ClassName:Db1
 * @Date: 2020/4/22 15:36
 * @Description: TODO
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class WebLogCtl {
   /* @Autowired
    private ZoneS zoneS;
    @GetMapping("/zone")
    public List<Map<String, Object>> zone_list(){
        return zoneS.zone_list();
    }*/
   @Autowired
    private WebLogSimpl webLogSimpl;
   @GetMapping("/log")
   public List<WebLog> log(){
       List<WebLog> list=webLogSimpl.list(null);
       log.info("data:{}",list);
       return list;
   }


}
