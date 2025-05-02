package com.awscore.autenticacionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.awscore.autenticacionservice")
public class AutenticacionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutenticacionServiceApplication.class, args);
	}

}
