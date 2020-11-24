package com.pengffe.springbootvue.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author pengffe  Email: pengffe@gmail.com
 * @descrption
 * @date
 */

@Configuration
//@ConfigurationProperties(prefix="spring.datasource.druid")
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DruidDataSource druid(){
        return new DruidDataSource();
    }

}
