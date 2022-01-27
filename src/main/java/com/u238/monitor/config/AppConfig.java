package com.u238.monitor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan("com.u238.monitor")
@PropertySource({"classpath:application.properties"})
public class AppConfig implements WebMvcConfigurer {


    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
