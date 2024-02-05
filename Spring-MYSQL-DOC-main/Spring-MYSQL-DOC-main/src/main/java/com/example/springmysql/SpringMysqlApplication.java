package com.example.springmysql;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				//alot attributes
				title = "Spring Boot REST API",
				version = "2.0",
				description = "Spring Boot REST API with MySQL",
				contact = @Contact(
						name = "Aditya",
				        email = "q2tHj@example.com"
		        )
		),
		externalDocs = @ExternalDocumentation(
				description = "Spring Boot REST API Documentation",
				url = "https://spring.io/projects/spring-boot"
)
)
public class SpringMysqlApplication {
	//configure spring beans
	//now we do this for modelmapper class 

	//@Bean
	//public ModelMapper modelMapper(){
	//	return new ModelMapper();
	//}

	public static void main(String[] args) {
		SpringApplication.run(SpringMysqlApplication.class, args);
	}

}
