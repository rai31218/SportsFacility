package com.sports.facility;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoReactiveAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
//,(exclude = {MongoAutoConfiguration.class, MongoReactiveAutoConfiguration.class})
@EnableEurekaClient
public class SportsFacilityApplication {

	@Value("${spring.data.mongodb.host}")
	 private  static String server;
	
	public static void main(String[] args) {
		SpringApplication.run(SportsFacilityApplication.class, args);
		System.out.println("server: "+server);
		
	}


	
    @Bean
    public ValidatingMongoEventListener validatingMongoEventListener() {
        return new ValidatingMongoEventListener(validator());
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }
    
	
	@Bean
	public RestTemplate restTemplate()
	{
		return new RestTemplate();
	}

}
