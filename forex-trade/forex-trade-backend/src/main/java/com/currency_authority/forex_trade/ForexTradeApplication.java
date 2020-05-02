package com.currency_authority.forex_trade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

//Spring boot application annotation is equal to  @Configuration, @EnableAutoConfiguration, and @ComponentScan

@SpringBootApplication
@EnableDiscoveryClient
public class ForexTradeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForexTradeApplication.class, args);
	}
}
