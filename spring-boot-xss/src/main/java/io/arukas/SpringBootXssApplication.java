package io.arukas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringBootXssApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootXssApplication.class, args);
	}
}
