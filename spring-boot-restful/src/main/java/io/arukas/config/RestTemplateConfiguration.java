package io.arukas.config;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {

	@Bean
	public RestTemplate restTemplate(ClientHttpRequestFactory clientHttpRequestFactory) {
		RestTemplate restTemplate = new RestTemplateBuilder().requestFactory(clientHttpRequestFactory)
				.additionalMessageConverters(new StringHttpMessageConverter(Charset.forName("UTF-8")))
				.additionalMessageConverters(new TextHtmlMappingJackson2HttpMessageConverter())
				.additionalMessageConverters(byteArrayHttpMessageConverter())
				.additionalMessageConverters(new MappingJackson2HttpMessageConverter())
				.build();
		return restTemplate;
	}

	@Bean
	public ClientHttpRequestFactory clientHttpRequestFactory() {
		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		clientHttpRequestFactory.setHttpClient(HttpClientBuilder.create().build());
		clientHttpRequestFactory.setConnectTimeout(10000);
		clientHttpRequestFactory.setReadTimeout(10000);
		clientHttpRequestFactory.setConnectionRequestTimeout(200);
		return clientHttpRequestFactory;
	}

	private static final class TextHtmlMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {
		public TextHtmlMappingJackson2HttpMessageConverter() {
			List<MediaType> mediaTypes = new ArrayList<>();
			mediaTypes.add(MediaType.TEXT_HTML);
			setSupportedMediaTypes(mediaTypes);
		}
	}
	
	@Bean
	public ByteArrayHttpMessageConverter byteArrayHttpMessageConverter() {
	    return new ByteArrayHttpMessageConverter();
	}
}
