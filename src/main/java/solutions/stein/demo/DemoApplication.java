package solutions.stein.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import jakarta.annotation.PreDestroy;

@SpringBootApplication
// @ImportRuntimeHints(DemoApplication.DemoApplicationRuntimeHints.class)
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

	// static class DemoApplicationRuntimeHints implements RuntimeHintsRegistrar {
	// 	public void registerHints(@SuppressWarnings("null") RuntimeHints hints, @Nullable ClassLoader classLoader) {
	// 		hints.reflection().registerType(AppStartStatResponse.class, hint -> 
	// 			hint.withMembers(
	// 				MemberCategory.INVOKE_PUBLIC_CONSTRUCTORS,
	// 				MemberCategory.INVOKE_PUBLIC_METHODS,
	// 				MemberCategory.INVOKE_DECLARED_METHODS
	// 			)
	// 		);
	// 	}
    // }
}
