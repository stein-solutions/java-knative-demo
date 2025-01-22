package solutions.stein.demo;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.EventListener;

import jakarta.annotation.PreDestroy;

@SpringBootApplication
// @ImportRuntimeHints(DemoApplication.DemoApplicationRuntimeHints.class)
public class DemoApplication {

	public static long AppStartTime = Instant.now().toEpochMilli();
	public static long AppReadyTime = 0;

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

	@EventListener(org.springframework.boot.context.event.ApplicationReadyEvent.class)
	public void AppStarted()
	{
		AppReadyTime = Instant.now().toEpochMilli();
		System.out.println("App started at: " + (Instant.now().toEpochMilli() - AppStartTime));
	}
}
