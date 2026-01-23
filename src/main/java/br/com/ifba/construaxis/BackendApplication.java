package br.com.ifba.construaxis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        // O Spring Boot lê as variáveis de ambiente automaticamente
        SpringApplication.run(BackendApplication.class, args);
    }
}