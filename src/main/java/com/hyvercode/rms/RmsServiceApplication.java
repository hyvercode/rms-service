package com.hyvercode.rms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin
public class RmsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RmsServiceApplication.class, args);
	}

}
