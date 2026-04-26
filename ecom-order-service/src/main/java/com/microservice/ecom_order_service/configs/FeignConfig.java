package com.microservice.ecom_order_service.configs;

import com.microservice.ecom_order_service.exceptions.CustomDecoder;
import feign.Logger;
import feign.Request;
import feign.Retryer;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;


@Configuration
public class FeignConfig {

    @Bean
    public Logger.Level feignLoggerLevel(){
        return Logger.Level.BASIC;
    }

    @Bean
    public Request.Options options(){
       return new Request.Options(Duration.ofMillis(3000),Duration.ofMillis(5000), true);
    }

    @Bean
    public Retryer retryer(){
        return new Retryer.Default(1L, 2L,3);
    }

    @Bean
    public ErrorDecoder decoder(){
        return  new CustomDecoder();
    }

}
