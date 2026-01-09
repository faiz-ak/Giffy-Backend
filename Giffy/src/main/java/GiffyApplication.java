package com.giffy.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class GiffyApplication {

	public static void main(String[] args) {
		SpringApplication.run(GiffyApplication.class, args);
	}

}
