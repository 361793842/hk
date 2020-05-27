package com.hk.hkhttpclient.Tools;


import com.hk.hkhttpclient.bean.Result;
import com.hk.hkhttpclient.constant.ErrCode;
import lombok.extern.slf4j.Slf4j;

/**
 * @author : mw
 * @ClassName:ResultUtil
 * @Date: 2019/6/22 21:34
 * @Description: TODO
 */
@Slf4j
public class ResultUtil {
    public static Result success(Object object) {
        Result result = new Result();
        result.setCode(ErrCode.SUCCESS.getCode());
        result.setMsg(ErrCode.SUCCESS.getMsg());
        result.setData(object);

       //log.info(GsonUtil.object2Json(result));
        return result;
    }

    public static Result success() {
        return success(null);
    }

    public static Result error(Integer code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static Result operationResult(int rs) {
        if (rs > 0) {
            return success();
        } else if (rs == 0) {
            return error(ErrCode.OPERATIONSFIELD.getCode(), ErrCode.OPERATIONSFIELD.getMsg());
        } else if (rs == -1) {
            return error(ErrCode.RECORDISEXISTS.getCode(), ErrCode.RECORDISEXISTS.getMsg());
        } else {
            return error(ErrCode.UNKNOW.getCode(), ErrCode.UNKNOW.getMsg());
        }
    }
}
