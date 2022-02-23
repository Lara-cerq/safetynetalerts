package com.safety.safetynetalerts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = { "com.safety" })
public class SafetynetalertsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SafetynetalertsApplication.class, args);
	}

}
