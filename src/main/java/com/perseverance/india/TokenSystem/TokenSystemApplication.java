package com.perseverance.india.TokenSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import com.perseverance.india.TokenSystem.services.EmailServices;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class TokenSystemApplication {

	public static void main(String[] args) {
		EmailServices mail = new EmailServices();
		
		SpringApplication.run(TokenSystemApplication.class, args);

		System.out.println("version: " + SpringBootVersion.getVersion());
		
		mail.message();

//		commonRepository.setFlagBasedRepo();
	}

}
