package com.hk.hkhttpclient.weblistenner;

import com.hk.hkhttpclient.Tools.GsonUtil;
import com.hk.hkhttpclient.Tools.HkSdkUtil;
import com.hk.hkhttpclient.enums.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author : muwei
 * @ClassName:Start
 * @Date: 2020/4/8 12:32
 * @Description: TODO
 */
@Component
@Slf4j
public class Start implements ApplicationRunner {
    private static HkSdkUtil hkSdkUtil=new HkSdkUtil();
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("========入侵报警监测系统开始运行=============");
        log.info("========版本：1.0.0=============");
        log.info("========开始重置事件订阅=============");
        int[] eventTypes={
                Event.ALARM_CS_BF.getCode(),
                Event.ALARM_CS_CF.getCode(),
                Event.ALARM_CS_CANCEL.getCode(),
                Event.ALARM_SZ_PL.getCode(),
                Event.ALARM_SZ_CPL.getCode(),
                Event.ALARM_SZ_ALARM.getCode()};
        hkSdkUtil.DelEvent(eventTypes);
        log.info("========开始订阅订阅事件=============");
        hkSdkUtil.AddEvent();
        log.info("========显示当前订阅事件=============");
        log.info(hkSdkUtil.SearchEvent());
        log.info("========软件加载完成，开始进入值守状态=============");


    }
}
