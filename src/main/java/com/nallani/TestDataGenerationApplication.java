package com.nallani;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class TestDataGenerationApplication {

	public static void main(String[] args) {

		SpringApplication.run(TestDataGenerationApplication.class, args);}
}
