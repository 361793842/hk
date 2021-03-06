package com.hk.hkhttpclient.configure;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : muwei
 * @ClassName:MybatisPlusConfig
 * @Date: 2020/5/13 16:32
 * @Description: TODO
 */
@Configuration
/*@MapperScan("com.hk.hkhttpclient.mapper")*/
public class MybatisPlusConfig {
    /*
     * 分页插件，自动识别数据库类型
     * 多租户，请参考官网【插件扩展】
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
