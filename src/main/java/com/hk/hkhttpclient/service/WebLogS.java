package com.hk.hkhttpclient.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hk.hkhttpclient.entity.WebLog;

import java.util.List;

/**
 * @author : muwei
 * @ClassName:WebLogS
 * @Date: 2020/5/13 17:30
 * @Description: TODO
 */
public interface WebLogS extends IService<WebLog> {
   List<WebLog> getAll();

}
