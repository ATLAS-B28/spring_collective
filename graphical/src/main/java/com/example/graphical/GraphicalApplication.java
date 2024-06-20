package com.example.graphical;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GraphicalApplication {

	/*@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}*/

	public static void main(String[] args) {
		SpringApplication.run(GraphicalApplication.class, args);
	}

}
