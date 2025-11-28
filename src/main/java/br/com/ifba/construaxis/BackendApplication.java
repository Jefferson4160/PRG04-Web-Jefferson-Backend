package br.com.ifba.construaxis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;



@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
        // Carrega o .env ANTES do Spring iniciar
        Dotenv dotenv = Dotenv.load();

        // Injeta as variáveis no sistema
        System.setProperty("DB_URL", dotenv.get("DB_URL"));
        System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
        System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));

        // Agora sobe o Spring Boot
        SpringApplication.run(BackendApplication.class, args);


	}

}
