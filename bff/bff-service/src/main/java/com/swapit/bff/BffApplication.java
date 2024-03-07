package com.swapit.bff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {
		"com.swapit.user.domain"
})
@EnableJpaRepositories(basePackages = {
		"com.swapit.user.repository"
})
@ComponentScan({
		"com.swapit.bff",
		"com.swapit.commons"
})
public class BffApplication {

	public static void main(String[] args) {
		SpringApplication.run(BffApplication.class, args);
	}

}
