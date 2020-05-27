package com.hk.hkhttpclient.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hk.hkhttpclient.entity.WebLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : muwei
 * @ClassName:WebLogMapper
 * @Date: 2020/5/13 17:20
 * @Description: TODO
 */
@Repository
public interface WebLogMapper extends BaseMapper<WebLog> {
    List<WebLog> getAll();
}
