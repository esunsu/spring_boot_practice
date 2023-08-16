package com.example.restfull;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ResTfullApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResTfullApplication.class, args);
	}

}
