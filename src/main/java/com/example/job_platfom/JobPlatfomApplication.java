package com.example.job_platfom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
@EnableEurekaServer
@SpringBootApplication
@EntityScan(basePackages = "com.example.job_platform.Entity")
public class JobPlatfomApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobPlatfomApplication.class, args);
	}

}
