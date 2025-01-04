package com.yash.HrManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.yash.HrManager")
public class YashHrManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(YashHrManagerApplication.class, args);
	}

}
