package com.hk.hkhttpclient.exceptions;

import com.hk.hkhttpclient.Tools.ResultUtil;
import com.hk.hkhttpclient.constant.ErrCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author : mw
 * @ClassName:GlobalExceptionHandler
 * @Date: 2019/3/7 09:15
 * @Description: TODO
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 全局异常捕捉处理
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Object errerHandler(HttpServletRequest request,
                               HttpServletResponse response,
                               Exception ex) {

        String errMsg=ex.getMessage().toString();
        log.info(ResultUtil.error(3, ex.getMessage()).toString());
        log.error(ex.getMessage());
        ex.printStackTrace();
        String str = ex.getClass().toString();
        if (errMsg!=null && errMsg.equals(ErrCode.USER_NOT_LOGIN.getMsg())){
            return ResultUtil.error(ErrCode.USER_NOT_LOGIN.getCode(),ErrCode.USER_NOT_LOGIN.getMsg());
        }
        if (errMsg!=null && errMsg.equals(ErrCode.USER_PASS_ERROR.getMsg())){
            return ResultUtil.error(ErrCode.USER_PASS_ERROR.getCode(),ErrCode.USER_PASS_ERROR.getMsg());
        }
        if (errMsg!=null && errMsg.equals(ErrCode.USER_TOKEN_ERROR.getMsg())){
            return ResultUtil.error(ErrCode.USER_TOKEN_ERROR.getCode(),ErrCode.USER_TOKEN_ERROR.getMsg());
        }
        if (errMsg!=null && errMsg.equals(ErrCode.REDIS_CONNECT_ERROR.getMsg())){
            return ResultUtil.error(ErrCode.REDIS_CONNECT_ERROR.getCode(),ErrCode.REDIS_CONNECT_ERROR.getMsg());
        }

        if (str != null && str.contains("HttpRequestMethodNotSupportedException")) {
            return ResultUtil.error(ErrCode.TYPEERR.getCode(), ErrCode.TYPEERR.getMsg());
        }
        if (str != null && str.contains("HttpMessageNotReadableException")) {
            return ResultUtil.error(ErrCode.PARAMERR.getCode(), ErrCode.PARAMERR.getMsg());
        }
        if (str != null && str.contains("ExpiredJwtException")) {
            return ResultUtil.error(ErrCode.TOKENEXPIRE.getCode(), ErrCode.TOKENEXPIRE.getMsg());
        }

        return ResultUtil.error(ErrCode.SERVERERR.getCode(), ErrCode.SERVERERR.getMsg());
    }


}
