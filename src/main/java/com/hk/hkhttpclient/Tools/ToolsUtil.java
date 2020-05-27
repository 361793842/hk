package com.hk.hkhttpclient.Tools;

import org.apache.commons.lang.RandomStringUtils;

import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author : muwei
 * @ClassName:ToolsUtil
 * @Date: 2020/4/9 16:07
 * @Description: TODO
 */
public class ToolsUtil {
    public static String RandomAlphanumeric(int count) {
        RandomStringUtils utils = new RandomStringUtils();
        String str = utils.randomAlphanumeric(count);
        return str;
    }
    public static String nowDate() {
        SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dd.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        String date = dd.format(new Date());
        return date;
    }
    /*//2013-10-24T11:39:00.000+0800
    DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    //2013-10-24T11:39:00.000+08:00
    DateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");*/
    public static String timeUTCFormat(String time){
        DateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        sdf1.setTimeZone(TimeZone.getTimeZone("UTC")); //获取时区
        SimpleDateFormat dd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dd.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        String dateStr="";
        try {
            Date dt =  sdf1.parse(time);
            dateStr=dd.format(dt);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateStr;
    }

    public static<E> void printList(List<E> list){
        for(Object t:list){
            System.out.println(GsonUtil.object2Json(t));
        }
    }
    public static void printObject(Object o){
        System.out.println(GsonUtil.object2Json(o));
    }
    public static Map<String, Object> BeanToMap(Object object) {
        Map<String, Object> keyValues = new HashMap<>();
        Method[] methods = object.getClass().getMethods();
        try {
            for (Method method : methods) {
                String methodName = method.getName();
                if (methodName.contains("get") && !methodName.contains("Class")) {
                    //invoke 执行get方法获取属性值
                    Object value = method.invoke(object);
                    //根据setXXXX 通过以下算法取得属性名称
                    String key = methodName.substring(methodName.indexOf("get") + 3);
                    Object temp = key.substring(0, 1).toString().toLowerCase();
                    key = key.substring(1);
                    //最终得到属性
                    key = temp + key;
                    keyValues.put(key, value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return keyValues;
    }
    public static String LocalIP(){
        String ip="";
        try {
            InetAddress address=InetAddress.getLocalHost();
            ip=address.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return ip;
    }

}
