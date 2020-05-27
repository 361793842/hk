package com.hk.hkhttpclient.http;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author : muwei
 * @ClassName:HttpClientUtil
 * @Date: 2020/4/14 11:03
 * @Description: TODO
 */
@Slf4j
public class HttpClientUtil {
    /**
     * 通过post方式调用http接口
     * @param url     url路径
     * @param jsonParam    json格式的参数
     * @return
     * @throws Exception
     */
    public static String sendPostByJson(String url, String jsonParam) {
        //声明返回结果
        String result = "";
        //开始请求API接口时间
        long startTime=System.currentTimeMillis();
        //请求API接口的响应时间
        long endTime= 0L;
        HttpEntity httpEntity = null;
        HttpResponse httpResponse = null;
        HttpClient httpClient = null;
        try {
            // 创建连接
            httpClient = HttpClientFactory.getInstance().getHttpClient();
            // 设置请求头和报文
            HttpPost httpPost = HttpClientFactory.getInstance().httpPost(url);
            Header header=new BasicHeader("Accept-Encoding",null);
            httpPost.setHeader(header);
            httpPost.addHeader("gwolf","sms");
            // 设置报文和通讯格式
            if (jsonParam==null){
                jsonParam="{}";
            }
            StringEntity stringEntity = new StringEntity(jsonParam,HttpConstant.UTF8_ENCODE);
            stringEntity.setContentEncoding(HttpConstant.UTF8_ENCODE);
            stringEntity.setContentType(HttpConstant.APPLICATION_JSON);
            httpPost.setEntity(stringEntity);
            //log.info("请求{}接口的参数为{}",url,jsonParam);
            //执行发送，获取相应结果
            httpResponse = httpClient.execute(httpPost);
            httpEntity= httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity);
        } catch (Exception e) {
            log.error("请求{}接口出现异常",url,e);
            /*if (reSend > 0) {
                log.info("请求{}出现异常:{}，进行重发。进行第{}次重发",url,e.getMessage(),(HttpConstant.REQ_TIMES-reSend +1));
                result = sendPostByJson(url, jsonParam, reSend - 1);
                if (result != null && !"".equals(result)) {
                    return result;
                }
            }*/
        }finally {
            try {
                EntityUtils.consume(httpEntity);
            } catch (IOException e) {
                log.error("http请求释放资源异常",e);
            }
        }
        //请求接口的响应时间
        endTime=System.currentTimeMillis();
        //log.info("直：{}",result);
        //log.info("请求{};内容:{};响应时间为:{}毫秒",url,result,(endTime-startTime));
        return result;

    }


    /**
     * 通过post方式调用http接口
     * @param url     url路径
     * @param map    json格式的参数
     * @return
     * @throws Exception
     */
    public static String sendPostByForm(String url, Map<String,Object> map) {
        //声明返回结果
        String result = "";
        //开始请求API接口时间
        long startTime=System.currentTimeMillis();
        //请求API接口的响应时间
        long endTime= 0L;
        HttpEntity httpEntity = null;
        UrlEncodedFormEntity entity = null;
        HttpResponse httpResponse = null;
        HttpClient httpClient = null;
        try {
            // 创建连接
            httpClient = HttpClientFactory.getInstance().getHttpClient();
            // 设置请求头和报文
            HttpPost httpPost = HttpClientFactory.getInstance().httpPost(url);
            httpPost.addHeader("gwolf","sms");
            //设置参数
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            Iterator iterator = map.entrySet().iterator();
            while(iterator.hasNext()){
                Map.Entry<String,Object> elem = (Map.Entry<String, Object>) iterator.next();
                list.add(new BasicNameValuePair(elem.getKey(),elem.getValue().toString()));
            }
            entity = new UrlEncodedFormEntity(list,HttpConstant.UTF8_ENCODE);
            httpPost.setEntity(entity);
           // log.info("请求{}接口的参数为{}",url,map);
            //执行发送，获取相应结果
            httpResponse = httpClient.execute(httpPost);
            httpEntity= httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity);
        } catch (Exception e) {
            log.error("请求{}接口出现异常",url,e);
        }finally {
            try {
                EntityUtils.consume(httpEntity);
            } catch (IOException e) {
                log.error("http请求释放资源异常",e);
            }
        }
        //请求接口的响应时间
        endTime=System.currentTimeMillis();
        //log.info("直：{}",result);
        //log.info("请求:{};内容:{},响应时间:{}毫秒",url,result,(endTime-startTime));
        return result;

    }
    /**
     * 通过post方式调用http接口
     * @param url     url路径
     * @param xmlParam    json格式的参数
     * @return
     * @throws Exception
     */
    public static String sendPostByXml(String url, String xmlParam) {
        //声明返回结果
        String result = "";
        //开始请求API接口时间
        long startTime = System.currentTimeMillis();
        //请求API接口的响应时间
        long endTime = 0L;
        HttpEntity httpEntity = null;
        HttpResponse httpResponse = null;
        HttpClient httpClient = null;
        try {
            // 创建连接
            httpClient = HttpClientFactory.getInstance().getHttpClient();
            // 设置请求头和报文
            HttpPost httpPost = HttpClientFactory.getInstance().httpPost(url);
            httpPost.addHeader("gwolf","sms");
            StringEntity stringEntity = new StringEntity(xmlParam, HttpConstant.UTF8_ENCODE);
            stringEntity.setContentEncoding(HttpConstant.UTF8_ENCODE);
            stringEntity.setContentType(HttpConstant.TEXT_XML);
            httpPost.setEntity(stringEntity);
            log.info("请求{}接口的参数为{}", url, xmlParam);
            //执行发送，获取相应结果
            httpResponse = httpClient.execute(httpPost);
            httpEntity = httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity,HttpConstant.UTF8_ENCODE);
        } catch (Exception e) {
            log.error("请求{}接口出现异常", url, e);
        } finally {
            try {
                EntityUtils.consume(httpEntity);
            } catch (IOException e) {
                log.error("http请求释放资源异常", e);
            }
            //请求接口的响应时间
            endTime = System.currentTimeMillis();
            log.info("请求{}接口的响应报文内容为{},本次请求API接口的响应时间为:{}毫秒", url, result, (endTime - startTime));
            return result;
        }

    }

    /**
     * 功能描述: get调用
     * @param:
     * @return:
     * @auther: mw
     * @date: 2020/4/14 11:22
     */
    public static String sendGetForm(String url,Map<String,Object> jsonParam){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        URI uri=null;
        CloseableHttpResponse response = null;
        //声明返回结果
        String result = "";
        //开始请求API接口时间
        long startTime = System.currentTimeMillis();
        //请求API接口的响应时间
        long endTime = 0L;
        HttpEntity responseEntity=null;
        HttpGet httpGet=null;
        try {
            // 将参数放入键值对类NameValuePair中,再放入集合中
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            // 遍历map，设置参数到list中
            if (jsonParam!=null){
                for (Map.Entry<String, Object> entry : jsonParam.entrySet()) {
                    params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()
                            .toString()));
                }
                uri = new URIBuilder(url).setParameters(params).build();
                httpGet=new HttpGet(uri);

            }else {
                httpGet=new HttpGet(url);
            }
            // 设置uri信息,并将参数集合放入uri;
            // 注:这里也支持一个键值对一个键值对地往里面放setParameter(String key, String value)


            httpGet.addHeader("gwolf","sms");
            // 由客户端执行(发送)Post请求
            response = httpClient.execute(httpGet);
            // 从响应模型中获取响应实体
            responseEntity = response.getEntity();
            if (responseEntity != null) {
               /* InputStream instreams = responseEntity.getContent();
                result = FunUtil.convertStreamToString(instreams);*/
                result= EntityUtils.toString(responseEntity);
            }

        } catch (Exception e) {
            log.error("请求{}接口出现异常", url, e);
        }finally {
            try {
                EntityUtils.consume(responseEntity);
            } catch (IOException e) {
                log.error("http请求释放资源异常", e);
            }
            //请求接口的响应时间
            endTime = System.currentTimeMillis();
           // log.info("请求:{};内容:{},响应时间为:{}毫秒", url, result, (endTime - startTime));
           // log.info("直：{}",result);
            return result;
        }

    }

    public static String sendGetPath(String url,String param){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        URI uri=null;
        CloseableHttpResponse response = null;
        //声明返回结果
        String result = "";
        //开始请求API接口时间
        long startTime = System.currentTimeMillis();
        //请求API接口的响应时间
        long endTime = 0L;
        HttpEntity responseEntity=null;
        try {
            url+="/"+param;
            HttpGet httpGet=new HttpGet(url);
            httpGet.addHeader("gwolf","sms");
            // 由客户端执行(发送)Post请求
            response = httpClient.execute(httpGet);
            // 从响应模型中获取响应实体
            responseEntity = response.getEntity();
            if (responseEntity != null) {
               /* InputStream instreams = responseEntity.getContent();
                result = FunUtil.convertStreamToString(instreams);*/
                result= EntityUtils.toString(responseEntity);
            }

        } catch (Exception e) {
            log.error("请求{}接口出现异常", url, e);
        }finally {
            try {
                EntityUtils.consume(responseEntity);
            } catch (IOException e) {
                log.error("http请求释放资源异常", e);
            }
            //请求接口的响应时间
            endTime = System.currentTimeMillis();
            //log.info("直：{}",result);
           // log.info("请求:{};内容:{};响应时间为:{}毫秒", url, result, (endTime - startTime));
            return result;
        }

    }
}
