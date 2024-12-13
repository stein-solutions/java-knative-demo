package de.viadee.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@SpringBootApplication
public class DemoApplication {



	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	private CustomMetrics customMetrics;

	@PreDestroy
	public void tearDown() {
		System.out.println("Shutting Down...............the ");
		this.customMetrics.updateSigtermReceived();
	}
}
