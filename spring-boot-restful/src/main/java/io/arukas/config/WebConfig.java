package io.arukas.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 静态资源配置
 * 
 * @author niuyuxian
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		// 默认配置
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/", "classpath:/public/",
				"classpath:/META-INF/resources/");

		// webjars 不建议这么用
		// registry.addResourceHandler("/assets/**")
		// .addResourceLocations("classpath:/META-INF/resources/webjars/").setCachePeriod(31556926);

		registry.addResourceHandler("/webjars/**")
			.addResourceLocations("classpath:/META-INF/resources/webjars/");

		registry.addResourceHandler("swagger-ui.html")
			.addResourceLocations("classpath:/META-INF/resources/");
		
		registry.addResourceHandler("/templates/**")
				.addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/templates/");
		
		registry.addResourceHandler("/static/**")
			.addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/");

	}
}
