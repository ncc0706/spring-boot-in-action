package io.arukas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@SpringBootApplication
public class SpringBootRestfulApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestfulApplication.class, args);
	}

	@GetMapping("/")
	public String index(ModelMap modelMap) {
		modelMap.put("message", "Undertow server started.");
		return "index";
	}
}
