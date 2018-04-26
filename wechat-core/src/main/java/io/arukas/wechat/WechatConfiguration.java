package io.arukas.wechat;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WechatConfiguration {

    @Bean
    public DemoService demoService() {
        System.out.println("init demo service");
        return new DemoService();
    }


}
