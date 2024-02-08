package com.perseverance.india.TokenSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class TokenSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(TokenSystemApplication.class, args);

		System.out.println("version: " + SpringBootVersion.getVersion());

//		commonRepository.setFlagBasedRepo();
	}

}
