package br.com.ifba.construaxis.backend.cnpj.controller;

import br.com.ifba.construaxis.backend.cnpj.dto.CnpjResponseDTO;
import br.com.ifba.construaxis.backend.cnpj.service.CnpjService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cnpj")
public class CnpjController {

    private final CnpjService cnpjService;

    public CnpjController(CnpjService cnpjService) {
        this.cnpjService = cnpjService;
    }

    @GetMapping("/{cnpj}")
    public CnpjResponseDTO consultar(@PathVariable String cnpj) {
        return cnpjService.consultarCnpj(cnpj);
    }
}
