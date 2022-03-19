package com.dreamsearcher.sender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SenderApplication {

	public static void main(String[] args) {
		SpringApplication.run(SenderApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate()
	{
		return new RestTemplate();
	}

	@Bean
	public SimpleMailMessage simpleMailMessage()
	{
		return new SimpleMailMessage();
	}

}
