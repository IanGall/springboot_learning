package com.ian.config;

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisPlusConfiguration {
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInnerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

}
