package br.com.ifba.construaxis.backend.cnpj.service;

import br.com.ifba.construaxis.backend.cnpj.dto.CnpjResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class CnpjService {

    private final WebClient webClient;

    public CnpjService(WebClient webClient) {
        this.webClient = webClient;
    }

    public CnpjResponseDTO consultarCnpj(String cnpj) {

        String cnpjLimpo = cnpj.replaceAll("\\D", ""); // remove . / -

        return webClient
                .get()
                .uri("https://receitaws.com.br/v1/cnpj/" + cnpjLimpo)
                .retrieve()
                .bodyToMono(CnpjResponseDTO.class)
                .block();
    }
}
