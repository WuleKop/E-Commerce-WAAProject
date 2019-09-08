package com.mum.ecommerce;

import com.mum.ecommerce.domain.Payment;
import org.apache.logging.log4j.LogManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
public class EcommerceApplication{

	private static final Logger logger = LogManager.getLogger(EcommerceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}


}
