package com.hk.hkhttpclient.controller;

import com.alibaba.fastjson.JSONObject;
import com.hikvision.artemis.sdk.ArtemisHttpUtil;
import com.hikvision.artemis.sdk.Client;
import com.hikvision.artemis.sdk.Request;
import com.hikvision.artemis.sdk.Response;
import com.hikvision.artemis.sdk.config.ArtemisConfig;
import com.hikvision.artemis.sdk.constant.Constants;
import com.hikvision.artemis.sdk.enums.Method;
import com.hikvision.artemis.sdk.util.HttpUtil;
import com.hikvision.artemis.sdk.util.MessageDigestUtil;
import com.hikvision.artemis.sdk.util.SignUtil;
import com.hk.hkhttpclient.configure.HkArtemisConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLDecoder;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.security.InvalidKeyException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.*;

import static com.hikvision.artemis.sdk.config.ArtemisConfig.appKey;
import static com.hikvision.artemis.sdk.config.ArtemisConfig.appSecret;
import static com.hikvision.artemis.sdk.util.HttpUtil.initUrl;
import static javax.xml.crypto.dsig.Transform.BASE64;
import static org.apache.commons.codec.digest.HmacUtils.hmacSha256;

/**
 * @author : muwei
 * @ClassName:AlarmPreviewUrl
 * @Date: 2020/3/12 16:31
 * @Description: TODO
 */
@Slf4j
public class AlarmPreviewUrl {
    public static String AlarmPreviewUrl() throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        /**
         * STEP1：设置平台参数，根据实际情况,设置host appkey appsecret 三个参数.
         */
      /*  ArtemisConfig.host = "192.168.0.136:443"; // artemis网关服务器ip端口
        appKey = "21833327";  // 秘钥appkey
        appSecret = "BlWF6fiMB8wakUa2oYzn";// 秘钥appSecret*/
        HkArtemisConfig.setConfig();

        /**
         * STEP2：设置OpenAPI接口的上下文
         */
        final String ARTEMIS_PATH = "/artemis";

        /**
         * STEP3：设置接口的URI地址
         */
        final String previewURLsApi = ARTEMIS_PATH + "/api/resource/v1/regions/root";
        Map<String, String> path = new HashMap<String, String>(2) {
            {
                put("https://", previewURLsApi);//根据现场环境部署确认是http还是https
            }
        };

        /**
         * STEP4：设置参数提交方式
         */
        String contentType = "application/json";

        /**
         * STEP5：组装请求参数
         */
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("treeCode", 0);
        String body = jsonBody.toJSONString();
        /**
         * STEP6：调用接口
         */
        String result =doPostStringArtemis(path, body, null, null, contentType , null);// post请求application/json类型参数

        return result;
    }
    public static String doPostStringArtemis(Map<String, String> path, String body, Map<String, String> querys, String accept, String contentType, Map<String, String> header) {
        String httpSchema = (String)path.keySet().toArray()[0];
        if (httpSchema != null && !StringUtils.isEmpty(httpSchema)) {
            String responseStr = null;

            try {
                Map<String, String> headers = new HashMap();
                if (StringUtils.isNotBlank(accept)) {
                    headers.put("Accept", accept);
                } else {
                    headers.put("Accept", "*/*");
                }

                if (StringUtils.isNotBlank(contentType)) {
                    headers.put("Content-Type", contentType);
                } else {
                    headers.put("Content-Type", "application/text;charset=UTF-8");
                }

                if (header != null) {
                    headers.putAll(header);
                }

                Request request = new Request(Method.POST_STRING, httpSchema + ArtemisConfig.host, (String)path.get(httpSchema), ArtemisConfig.appKey, ArtemisConfig.appSecret, Constants.DEFAULT_TIMEOUT);
                request.setHeaders(headers);
                request.setQuerys(querys);
                request.setStringBody(body);
                Response response = execute(request);
                responseStr = getResponseResult(response);
            } catch (Exception var11) {
                log.error("the Artemis PostString Request is failed[doPostStringArtemis]", var11);
            }

            return responseStr;
        } else {
            throw new RuntimeException("http和https参数错误httpSchema: " + httpSchema);
        }
    }
    public static Response execute(Request request) throws Exception {
        switch(request.getMethod()) {
            case GET:
                return HttpUtil.httpGet(request.getHost(), request.getPath(), request.getTimeout(), request.getHeaders(), request.getQuerys(), request.getSignHeaderPrefixList(), request.getAppKey(), request.getAppSecret());
            case GET_RESPONSE:
                return HttpUtil.httpImgGet(request.getHost(), request.getPath(), request.getTimeout(), request.getHeaders(), request.getQuerys(), request.getSignHeaderPrefixList(), request.getAppKey(), request.getAppSecret());
            case POST_FORM:
                return HttpUtil.httpPost(request.getHost(), request.getPath(), request.getTimeout(), request.getHeaders(), request.getQuerys(), request.getBodys(), request.getSignHeaderPrefixList(), request.getAppKey(), request.getAppSecret());
            case POST_FORM_RESPONSE:
                return HttpUtil.httpImgPost(request.getHost(), request.getPath(), request.getTimeout(), request.getHeaders(), request.getQuerys(), request.getBodys(), request.getSignHeaderPrefixList(), request.getAppKey(), request.getAppSecret());
            case POST_STRING:
                return httpPost(request.getHost(), request.getPath(), request.getTimeout(), request.getHeaders(), request.getQuerys(), request.getStringBody(), request.getSignHeaderPrefixList(), request.getAppKey(), request.getAppSecret());
            case POST_STRING_RESPONSE:
                return HttpUtil.httpImgPost(request.getHost(), request.getPath(), request.getTimeout(), request.getHeaders(), request.getQuerys(), request.getStringBody(), request.getSignHeaderPrefixList(), request.getAppKey(), request.getAppSecret());
            case POST_BYTES:
                return HttpUtil.httpPost(request.getHost(), request.getPath(), request.getTimeout(), request.getHeaders(), request.getQuerys(), request.getBytesBody(), request.getSignHeaderPrefixList(), request.getAppKey(), request.getAppSecret());
            case PUT_STRING:
                return HttpUtil.httpPut(request.getHost(), request.getPath(), request.getTimeout(), request.getHeaders(), request.getQuerys(), request.getStringBody(), request.getSignHeaderPrefixList(), request.getAppKey(), request.getAppSecret());
            case PUT_BYTES:
                return HttpUtil.httpPut(request.getHost(), request.getPath(), request.getTimeout(), request.getHeaders(), request.getQuerys(), request.getBytesBody(), request.getSignHeaderPrefixList(), request.getAppKey(), request.getAppSecret());
            case DELETE:
                return HttpUtil.httpDelete(request.getHost(), request.getPath(), request.getTimeout(), request.getHeaders(), request.getQuerys(), request.getSignHeaderPrefixList(), request.getAppKey(), request.getAppSecret());
            default:
                throw new IllegalArgumentException(String.format("unsupported method:%s", request.getMethod()));
        }
    }
    private static Response convert(HttpResponse response) throws IOException {
        Response res = new Response();
        if (null != response) {
            res.setStatusCode(response.getStatusLine().getStatusCode());
            Header[] var2 = response.getAllHeaders();
            int var3 = var2.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                Header header = var2[var4];
                res.setHeader(header.getName(), MessageDigestUtil.iso88591ToUtf8(header.getValue()));
            }

            res.setContentType(res.getHeader("Content-Type"));
            res.setRequestId(res.getHeader("X-Ca-Request-Id"));
            res.setErrorMessage(res.getHeader("X-Ca-Error-Message"));
            if (response.getEntity() == null) {
                res.setBody((String)null);
            } else {
                res.setBody(readStreamAsStr(response.getEntity().getContent()));
            }
        } else {
            res.setStatusCode(500);
            res.setErrorMessage("No Response");
        }

        return res;
    }
    public static String readStreamAsStr(InputStream is) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        WritableByteChannel dest = Channels.newChannel(bos);
        ReadableByteChannel src = Channels.newChannel(is);
        ByteBuffer bb = ByteBuffer.allocate(4096);

        while(src.read(bb) != -1) {
            bb.flip();
            dest.write(bb);
            bb.clear();
        }

        src.close();
        dest.close();
        return new String(bos.toByteArray(), "UTF-8");
    }
    public static Response httpPost(String host, String path, int connectTimeout, Map<String, String> headers, Map<String, String> querys, String body, List<String> signHeaderPrefixList, String appKey, String appSecret) throws Exception {
        String contentType = (String)headers.get("Content-Type");
        if ("application/x-www-form-urlencoded;charset=UTF-8".equals(contentType)) {
            Map<String, String> paramMap = strToMap(body);
            String modelDatas = (String)paramMap.get("modelDatas");
            if (StringUtils.isNotBlank(modelDatas)) {
                paramMap.put("modelDatas", URLDecoder.decode(modelDatas));
            }

            headers = initialBasicHeader("POST", path, headers, querys, paramMap, signHeaderPrefixList, appKey, appSecret);
        } else {
            headers = initialBasicHeader("POST", path, headers, querys, (Map)null, signHeaderPrefixList, appKey, appSecret);
        }

        HttpClient httpClient = wrapClient(host);
        httpClient.getParams().setParameter("http.connection.timeout", getTimeout(connectTimeout));
        HttpPost post = new HttpPost(initUrl(host, path, querys));
        Iterator var12 = headers.entrySet().iterator();

        while(var12.hasNext()) {
            Map.Entry<String, String> e = (Map.Entry)var12.next();
            post.addHeader((String)e.getKey(), MessageDigestUtil.utf8ToIso88591((String)e.getValue()));
        }

        if (StringUtils.isNotBlank(body)) {
            post.setEntity(new StringEntity(body, "UTF-8"));
        }

        return convert(httpClient.execute(post));
    }
    private static void sslClient(HttpClient httpClient) {
        try {
            SSLContext ctx = SSLContext.getInstance("TLS");
            X509TrustManager tm = new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkClientTrusted(X509Certificate[] xcs, String str) {
                }

                public void checkServerTrusted(X509Certificate[] xcs, String str) {
                }
            };
            ctx.init((KeyManager[])null, new TrustManager[]{tm}, (SecureRandom)null);
            SSLSocketFactory ssf = new SSLSocketFactory(ctx);
            ssf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            ClientConnectionManager ccm = httpClient.getConnectionManager();
            SchemeRegistry registry = ccm.getSchemeRegistry();
            registry.register(new Scheme("https", 443, ssf));
        } catch (KeyManagementException var6) {
            throw new RuntimeException(var6);
        } catch (NoSuchAlgorithmException var7) {
            throw new RuntimeException(var7);
        }
    }
    private static int getTimeout(int timeout) {
        return timeout == 0 ? Constants.DEFAULT_TIMEOUT : timeout;
    }
    private static HttpClient wrapClient(String host) {
        HttpClient httpClient = new DefaultHttpClient();
        if (host.startsWith("https://")) {
            sslClient(httpClient);
        }

        return httpClient;
    }
    private static Map<String, String> strToMap(String str) {
        HashMap map = new HashMap();

        try {
            String[] params = str.split("&");
            String[] var3 = params;
            int var4 = params.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                String param = var3[var5];
                String[] a = param.split("=");
                map.put(a[0], a[1]);
            }

            return map;
        } catch (Exception var8) {
            throw new RuntimeException(var8);
        }
    }
    private static Map<String, String> initialBasicHeader(String method, String path, Map<String, String> headers, Map<String, String> querys, Map<String, String> bodys, List<String> signHeaderPrefixList, String appKey, String appSecret) throws MalformedURLException {
        if (headers == null) {
            headers = new HashMap();
        }
        /*log.info("解析后："+SignUtil.sign(appSecret, method, path, (Map)headers, querys, bodys, signHeaderPrefixList));*/
       // ((Map)headers).put("x-ca-timestamp", String.valueOf((new Date()).getTime()));
       // ((Map)headers).put("x-ca-nonce", UUID.randomUUID().toString());
        ((Map)headers).put("x-ca-key", appKey);
        String str=SignUtil.sign(appSecret, method, path, (Map)headers, querys, bodys, signHeaderPrefixList);
        ((Map)headers).put("x-ca-signature", str);



        log.info("解析后:{}"+str);
        log.info("解析后:{}"+headers);

        return (Map)headers;
    }
    private static String getResponseResult(Response response) {
        String responseStr = null;
        int statusCode = response.getStatusCode();
        if (!String.valueOf(statusCode).startsWith("2") && !String.valueOf(statusCode).startsWith("3")) {
            String msg = response.getErrorMessage();
            responseStr = response.getBody();
            log.error("the Artemis Request is Failed,statusCode:" + statusCode + " errorMsg:" + msg);
        } else {
            responseStr = response.getBody();
            log.info("the Artemis Request is Success,statusCode:" + statusCode + " SuccessMsg:" + response.getBody());
        }

        return responseStr;
    }
    public static void main(String[] args) {
        String result = null;
        try {
            result = AlarmPreviewUrl();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("result结果示例: " + result);
    }
}
