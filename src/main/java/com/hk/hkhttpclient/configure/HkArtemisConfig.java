package com.hk.hkhttpclient.configure;

import com.hikvision.artemis.sdk.config.ArtemisConfig;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author : muwei
 * @ClassName:HkArtemisConfig
 * @Date: 2020/4/2 13:41
 * @Description: TODO
 */
public class HkArtemisConfig {
    private static String host="192.168.0.136";
    private static int port=443;
    private static String appkey="20398264";
    private static String appsecret="G75EQhspAUO5B3OuQJEP";
    public static void setConfig(){
        /**
         * STEP1：设置平台参数，根据实际情况,设置host appkey appsecret 三个参数.
         */
        ArtemisConfig.host = host+":"+port; // artemis网关服务器ip端口
        ArtemisConfig.appKey = appkey;  // 秘钥appkey
        ArtemisConfig.appSecret = appsecret;// 秘钥appSecret
    }
}
