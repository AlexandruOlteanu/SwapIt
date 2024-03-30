package com.swapit.chron;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({
		"com.swapit.chron",
		"com.swapit.commons"
})
public class ChronApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChronApplication.class, args);
	}

}
