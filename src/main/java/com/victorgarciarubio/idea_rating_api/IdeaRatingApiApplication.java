package com.victorgarciarubio.idea_rating_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication()
public class IdeaRatingApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(IdeaRatingApiApplication.class, args);
	}

}
