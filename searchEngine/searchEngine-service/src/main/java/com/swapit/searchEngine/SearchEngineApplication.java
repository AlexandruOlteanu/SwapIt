package com.swapit.searchEngine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({
		"com.swapit.searchEngine",
		"com.swapit.commons"
})
@EnableFeignClients(basePackages = {
		"com.swapit.product.service"
})
@EnableCaching
public class SearchEngineApplication {

	public static void main(String[] args) {
		SpringApplication.run(SearchEngineApplication.class, args);
	}

}
