package br.com.ifba.construaxis.backend.external;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ExternaService {

    private final WebClient webClient;

    public ExternaService(WebClient webClient) {
        this.webClient = webClient;
    }

    public String buscarDados() {
        return webClient
                .get()
                .uri("https://jsonplaceholder.typicode.com/posts/1")
                .retrieve()
                .bodyToMono(String.class)
                .block(); // ← sincrono
    }
}
