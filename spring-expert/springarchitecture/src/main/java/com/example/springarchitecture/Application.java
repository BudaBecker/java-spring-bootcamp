package com.example.springarchitecture;

import org.springframework.boot.Banner.Mode;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		// Standard method to start a Spring application
		// SpringApplication.run(Application.class, args);

		// Builder Method
		SpringApplicationBuilder builder = new SpringApplicationBuilder(Application.class);

		// Starter configurations
		builder.bannerMode(Mode.OFF);
		builder.profiles(args); // not sure what builder.profiles does, but it does cool shit.

		// run
		builder.run(args);

	}

}
