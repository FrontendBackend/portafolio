package com.portafolio.portafoliobackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class PortafolioBackendApplication extends SpringBootServletInitializer {

	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(PortafolioBackendApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(PortafolioBackendApplication.class, args);
	}

}
