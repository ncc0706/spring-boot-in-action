package io.arukas.listener;

import io.arukas.CalendarPropertySource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * Created by IntelliJ IDEA. <br/>
 *
 * @Auther: 牛玉贤 <br/>
 * @Date: 2018/12/2 12:25 <br/>
 * @Email: ncc0706@gmail.com <br/>
 */
public class ApplicationStartedEventListener01 implements EnvironmentPostProcessor, ApplicationListener<ApplicationStartedEvent> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        logger.debug("{}", ApplicationStartedEventListener01.class.getName());
    }

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        CalendarPropertySource.addToEnvironment(environment);
    }
}
