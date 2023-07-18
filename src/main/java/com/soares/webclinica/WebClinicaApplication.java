package com.soares.webclinica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({ "com.soares.webclinica.controller" })
@SpringBootApplication (scanBasePackageClasses = WebClinicaApplication.class)
public class WebClinicaApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebClinicaApplication.class, args);
	}

}
