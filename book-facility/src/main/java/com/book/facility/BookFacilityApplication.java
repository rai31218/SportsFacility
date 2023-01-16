package com.book.facility;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BookFacilityApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookFacilityApplication.class, args);
	}

}
