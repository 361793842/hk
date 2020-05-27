package com.hk.hkhttpclient.Tools;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * @author : mw
 * @ClassName:GsonUtil
 * @Date: 2019/6/18 10:56
 * @Description: TODO
 */
public class GsonUtil {
    public static String object2Json(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    public static <T> T json2Object(String json, Class<T> clazz) {
       /* Gson gson = new Gson();
        return gson.fromJson(json, clazz);*/
        return JSON.parseObject(json,clazz);
    }

    //new TypeToken<List<Person>>(){}.getType()
    public static <T> T json2Object(String json, Type type) {
        Gson gson = new Gson();
        return gson.fromJson(json, type);
    }
}
