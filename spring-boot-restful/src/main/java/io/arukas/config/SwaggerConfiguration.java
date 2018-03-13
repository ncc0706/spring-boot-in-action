package io.arukas.config;

import static springfox.documentation.builders.PathSelectors.ant;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.endpoint.mvc.EndpointHandlerMapping;
import org.springframework.boot.actuate.endpoint.mvc.MvcEndpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//api接口只在开发环境和测试环境生效
@Profile({"test", "dev"})
@Configuration
@EnableSwagger2
public class SwaggerConfiguration{
	
	// 自定义的 actuatorEndpoint path
	@Value("${management.context-path}")
	private String ops;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Bean
    public Docket restApi(final EndpointHandlerMapping actuatorEndpointHandlerMapping) {
		ApiSelectorBuilder builder = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 排除 error 相关的 url
                .paths(Predicates.and(ant("/**"), Predicates.not(ant("/error"))));
                
	    // Ignore the spring-boot-actuator endpoints:
	    Set<MvcEndpoint> endpoints = actuatorEndpointHandlerMapping.getEndpoints();
	    endpoints.forEach(endpoint -> {
	        String path = endpoint.getPath();
	        if(logger.isDebugEnabled()){
	        	logger.debug("excluded path for swagger {}", ops + path);
	        }
	        builder.paths(Predicates.not(PathSelectors.regex(ops + path + ".*")));
	    });
	    
	    return builder.build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("farmers service API")
                .description("farmers service API doc")
                .contact(new Contact("niuyuxian", "http:/www.xlinyu.com", "niuyuxian@163.com"))
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .version("1.0.0")
                .build();
    }
}
