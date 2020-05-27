package com.hk.hkhttpclient.configure;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author : muwei
 * @ClassName:ReadCnf
 * @Date: 2020/4/14 13:43
 * @Description: TODO
 */
@Component
@Getter
public class ReadCnf {
   /* @Value("${httpserver.ip}")
    private String ip;
    @Value("${httpserver.port}")
    private int port;*/
    @Value("${httpAddr}")
    private String httpAddr;
    @Value("${event.recv.ip}")
    private String eventRecvIp;
    @Value("${server.port}")
    private int serverPort;
}
