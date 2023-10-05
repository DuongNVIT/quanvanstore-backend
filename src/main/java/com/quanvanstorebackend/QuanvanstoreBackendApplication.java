package com.quanvanstorebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class QuanvanstoreBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuanvanstoreBackendApplication.class, args);
	}

}
