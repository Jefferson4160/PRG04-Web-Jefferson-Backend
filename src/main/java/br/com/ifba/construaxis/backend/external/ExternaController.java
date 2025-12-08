package br.com.ifba.construaxis.backend.external;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExternaController {

    private final ExternaService externaService;

    public ExternaController(ExternaService externaService) {
        this.externaService = externaService;
    }

    @GetMapping("/teste-api-externa")
    public String testarAPI() {
        return externaService.buscarDados();
    }
}
