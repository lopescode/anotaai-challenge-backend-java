package com.lopes.anotaai_challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class AnotaaiChallengeApplication {
	public static void main(String[] args) {
		SpringApplication.run(AnotaaiChallengeApplication.class, args);
	}
}
