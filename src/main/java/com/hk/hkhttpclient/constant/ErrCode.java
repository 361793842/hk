package com.hk.hkhttpclient.constant;

/**
 * @author : muwei
 * @ClassName:ErrCode
 * @Date: 2019/8/27 12:47
 * @Description: TODO
 */
public enum ErrCode {
    UNKNOW(-1, "未知错误"),
    SUCCESS(0, "操作成功"),
    TOKENNULL(1, "token信息不存在"),
    PARAMERR(2, "参数异常，请检查必传参数是否正确"),
    SERVERERR(3, "服务器响应失败"),
    TYPEERR(4, "非GET方式获取数据"),
    TOKENERR(5, "token信息错误,获取数据非法"),
    OPERATIONSFIELD(6, "操作不成功"),
    RECORDISEXISTS(7, "记录已经存在"),
    LOGINERROR(8, "登陆失败,用户名或者密码错误"),
    CONNECTIONREDISERRORR(9, "连接redis服务失败"),
    TOKENEXPIRE(10, "token过期，请重新登陆"),


    //用户2001-3000
    USER_NOT_LOGIN(2001,"用户还没有登陆"),
    USER_PASS_ERROR(2002,"登陆用户名，密码错误"),
    USER_TOKEN_ERROR(2003,"token信息错误,获取数据非法"),
    USER_TOKEN_TIMEOUT(2004,"token信息过期，重新登陆"),
    //参数3001-4000
    PARAM_IS_NULL(3001,"参数为空"),
    //redis 4001~5001
    REDIS_CONNECT_ERROR(4001,"redis服务连接失败"),
    //hk 6001~7001
    HK_ERROR(6001,"连接hk error!")

    ;


    private int code;

    private String msg;


    ErrCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ErrCode{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
