package solutions.stein.demo;

import org.springframework.aot.hint.ExecutableMode;
import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.aot.hint.annotation.RegisterReflection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportRuntimeHints;
import org.springframework.lang.Nullable;

import jakarta.annotation.PreDestroy;

import solutions.stein.demo.controller.AppStartStatResponse;

@SpringBootApplication
@ImportRuntimeHints(DemoApplication.DemoApplicationRuntimeHints.class)
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

	static class DemoApplicationRuntimeHints implements RuntimeHintsRegistrar {
		public void registerHints(@SuppressWarnings("null") RuntimeHints hints, @Nullable ClassLoader classLoader) {
			hints.reflection().registerType(AppStartStatResponse.class, hint -> 
				hint.withMembers(
					MemberCategory.INVOKE_PUBLIC_CONSTRUCTORS,
					MemberCategory.INVOKE_PUBLIC_METHODS,
					MemberCategory.INVOKE_DECLARED_METHODS
				)
			);
		}
    }
}
