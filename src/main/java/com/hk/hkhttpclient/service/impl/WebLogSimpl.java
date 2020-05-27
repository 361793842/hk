package com.hk.hkhttpclient.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hk.hkhttpclient.constant.DBSource;
import com.hk.hkhttpclient.entity.WebLog;
import com.hk.hkhttpclient.mapper.WebLogMapper;
import com.hk.hkhttpclient.service.WebLogS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : muwei
 * @ClassName:WebLogSimpl
 * @Date: 2020/5/13 17:31
 * @Description: TODO
 */
@Service
public class WebLogSimpl extends ServiceImpl<WebLogMapper, WebLog> implements WebLogS {
    @Autowired
    WebLogMapper webLogMapper;
    @Override
    public List<WebLog> getAll() {
        return webLogMapper.getAll();
    }
}
