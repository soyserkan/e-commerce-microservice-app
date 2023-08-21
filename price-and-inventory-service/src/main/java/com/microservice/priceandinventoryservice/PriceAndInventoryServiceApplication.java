package com.microservice.priceandinventoryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PriceAndInventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PriceAndInventoryServiceApplication.class, args);
	}

}
