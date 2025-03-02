package com.example.demo.infrastructure.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {

	@Bean
	OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info().title("Supermarket API")
				.description("API RESTful para gerenciamento e controle de produtos.").version("1.0.0")
				.contact(new Contact().name("Samuel Maciel da Silva").email("samuelmsilva@outlook.com.br")
						.url("https://github.com/samuelmsilva2v"))
				.license(new License().name("Apache 2.0").url("http://springdoc.org")));
	}
}
