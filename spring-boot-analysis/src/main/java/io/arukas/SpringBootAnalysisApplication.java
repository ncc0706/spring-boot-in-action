package io.arukas;

import io.arukas.listener.ApplicationStartedEventListener01;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootAnalysisApplication {

    public static void main(String[] args) {
//        SpringApplication.run(SpringBootAnalysisApplication.class, args);

        SpringApplication application = new SpringApplication(SpringBootAnalysisApplication.class);
        application.addListeners(new ApplicationStartedEventListener01());

//        application.addInitializers();

        application.run(args);
    }
}
