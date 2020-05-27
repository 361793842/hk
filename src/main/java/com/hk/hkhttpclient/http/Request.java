package com.hk.hkhttpclient.http;

import com.hk.hkhttpclient.Tools.GsonUtil;
import com.hk.hkhttpclient.Tools.SpringUtil;
import com.hk.hkhttpclient.bean.Result;
import com.hk.hkhttpclient.configure.ReadCnf;

/**
 * @author : muwei
 * @ClassName:Request
 * @Date: 2020/4/14 10:26
 * @Description: TODO
 */
public class Request {
    private static ReadCnf readCnf= SpringUtil.getBean(ReadCnf.class);

    public Request(){
    }
    /**
     * 功能描述:推送告警
     * @param:
     * @return:
     * @auther: mw
     * @date: 2020/5/26 11:39
     */
    public static Result pushAlarm(Object object){
        String url=readCnf.getHttpAddr();
        String rs=HttpClientUtil.sendPostByJson(url, GsonUtil.object2Json(object));
        Result result=GsonUtil.json2Object(rs,Result.class);
        return result;
    }
}
