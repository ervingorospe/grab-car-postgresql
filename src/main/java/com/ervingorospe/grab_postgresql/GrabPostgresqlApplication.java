package com.ervingorospe.grab_postgresql;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GrabPostgresqlApplication {

	public static void main(String[] args) {

		String isKubernetes = System.getenv("KUBERNETES_SERVICE_HOST");

		if (isKubernetes == null) {
			// Running locally (IntelliJ)
			Dotenv dotenv = Dotenv.configure().load();
			System.setProperty("DB_URL", dotenv.get("DB_URL"));
			System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
			System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
			System.setProperty("DB_DRIVER", dotenv.get("DB_DRIVER"));
		} else {
			// Running in Kubernetes (use environment variables)
			System.setProperty("DB_URL", System.getenv("DB_URL"));
			System.setProperty("DB_USERNAME", System.getenv("DB_USERNAME"));
			System.setProperty("DB_PASSWORD", System.getenv("DB_PASSWORD"));
			System.setProperty("DB_DRIVER", System.getenv("DB_DRIVER"));
		}

		SpringApplication.run(GrabPostgresqlApplication.class, args);
	}

}
