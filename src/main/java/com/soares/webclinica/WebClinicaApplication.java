package com.soares.webclinica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication (scanBasePackageClasses = WebClinicaApplication.class)
public class WebClinicaApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebClinicaApplication.class, args);
	}
}
