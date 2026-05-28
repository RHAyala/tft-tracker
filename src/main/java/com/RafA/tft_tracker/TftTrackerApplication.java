package com.RafA.tft_tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.hibernate.autoconfigure.HibernateJpaAutoConfiguration;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication
public class TftTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TftTrackerApplication.class, args);
	}

}
