package io.arukas.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * Created by IntelliJ IDEA. <br/>
 *
 * @Auther: 牛玉贤 <br/>
 * @Date: 2018/12/2 11:59 <br/>
 * @Email: ncc0706@gmail.com <br/>
 */

// 越小越先执行.
//@Order(-1)
public class InitListener01 implements SpringApplicationRunListener {

    private final Logger logger = LoggerFactory.getLogger(getClass());


    // 自己实现的类必须含有以下构造函数，才能被Boot调用
    public InitListener01(SpringApplication application, String[] args) {
    }

    @Override
    public void starting() {
        logger.info("{} starting...", InitListener01.class.getName());
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {

    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {

    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {

    }

    @Override
    public void started(ConfigurableApplicationContext context) {
        logger.debug("{} started...", InitListener01.class.getName());
    }

    @Override
    public void running(ConfigurableApplicationContext context) {
        logger.debug("{} running...", InitListener01.class.getName());
    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {

    }
}
