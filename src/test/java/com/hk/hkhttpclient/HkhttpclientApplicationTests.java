package com.hk.hkhttpclient;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hk.hkhttpclient.Tools.ToolsUtil;
import com.hk.hkhttpclient.entity.WebLog;
import com.hk.hkhttpclient.service.impl.WebLogSimpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.*;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class HkhttpclientApplicationTests {
    @Autowired
    private WebLogSimpl webLogSimpl;
    @Test
    public void test(){
        List<WebLog> list=webLogSimpl.list(null);
        ToolsUtil.printList(list);
    }
    @Test
    public void test2(){
        int count=webLogSimpl.count(null);
        log.info("count:{}",count);
    }
    @Test
    public void test3(){
        QueryWrapper<WebLog> qw=new QueryWrapper<>();
        qw.gt("id",500);
        qw.orderBy(true,true,"id");
        List<WebLog> list=webLogSimpl.list(qw);
        ToolsUtil.printList(list);
    }
    @Test
    public void test4(){
        List<WebLog> list=webLogSimpl.getAll();
        ToolsUtil.printList(list);
    }
    @Test
    public void pageLog(){
        int pageNo=1;
        int pageSize=2;
        IPage<WebLog> page = new Page<>(pageNo, pageSize);
        QueryWrapper<WebLog> wrapper = new QueryWrapper<>();
        wrapper.ge("id",500);
        wrapper.orderByAsc("id");
        IPage<WebLog> userIPage = webLogSimpl.page(page,wrapper);
        ToolsUtil.printObject(userIPage);
    }
    @Test
    public void save(){
        WebLog webLog=new WebLog();
        webLog.setIp("127.0.0.1");
        webLog.setContent("plush测试");
        webLog.setOperator("admin");
        webLog.setTime(ToolsUtil.nowDate());
        boolean rs=webLogSimpl.save(webLog);
        log.info("rs:{}",rs);
    }
    @Test
    public void save2(){
        WebLog webLog=new WebLog();
        webLog.setIp("127.0.0.1");
        webLog.setContent("plush测试");
        webLog.setOperator("admin");
        webLog.setTime(ToolsUtil.nowDate());
        List<WebLog> list=new ArrayList<>();
        Collection<WebLog> collection=new ArrayList<>();
        collection.add(webLog);
        collection.add(webLog);

        boolean rs=webLogSimpl.saveBatch(collection);
        log.info("rs:{}",rs);
    }
    @Test
    public void ip(){
        try {
            InetAddress addr = InetAddress.getLocalHost();
            System.out.println("Local HostAddress: "+addr.getHostAddress());
                    String hostname = addr.getHostName();
            System.out.println("Local host name: "+hostname);
            Properties props = System.getProperties();
            System.out.println("操作系统的名称：" + props.getProperty("os.name"));
            System.out.println("操作系统的版本号：" + props.getProperty("os.version"));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void getLocalIP() {
        List<String> ipList = new ArrayList<String>();
        InetAddress ip = null;
        try {
            Enumeration<NetworkInterface> netInterfaces = (Enumeration<NetworkInterface>) NetworkInterface.getNetworkInterfaces();
            while (netInterfaces.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
                // 遍历所有ip
                Enumeration<InetAddress> ips = ni.getInetAddresses();
                while (ips.hasMoreElements()) {
                    ip = (InetAddress) ips.nextElement();
                    if (null == ip || "".equals(ip)) {
                        continue;
                    }
                    String sIP = ip.getHostAddress();
                    if(sIP == null || sIP.indexOf(":") > -1) {
                        continue;
                    }
                    ipList.add(sIP);
                    System.out.println(sIP);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
       // return ipList;
    }
    public  InetAddress getFirstNonLoopbackAddress(boolean preferIpv4, boolean preferIPv6) throws SocketException {
        Enumeration en = NetworkInterface.getNetworkInterfaces();
        while (en.hasMoreElements()) {
            NetworkInterface i = (NetworkInterface) en.nextElement();
            for (Enumeration en2 = i.getInetAddresses(); en2.hasMoreElements();) {
                InetAddress addr = (InetAddress) en2.nextElement();
                if (!addr.isLoopbackAddress()) {
                    if (addr instanceof Inet4Address) {
                        if (preferIPv6) {
                            continue;
                        }
                        return addr;
                    }
                    if (addr instanceof Inet6Address) {
                        if (preferIpv4) {
                            continue;
                        }
                        return addr;
                    }
                }
            }
        }
        return null;
    }
    @Test
    public void iip(){
        for (int i = 0; i < 30; i++) {
            int a=(int)(Math.random()*20)+1;
            log.info("sdsdsd:{}",a);
        }
       /* try {
            log.info(getFirstNonLoopbackAddress(true,false).getHostAddress());
        } catch (SocketException e) {
            e.printStackTrace();
        }*/
    }
}
