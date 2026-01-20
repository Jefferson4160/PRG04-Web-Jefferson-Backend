package br.com.ifba.construaxis.backend.parceiro.controller;

import br.com.ifba.construaxis.backend.parceiro.entity.Fornecedor;
import br.com.ifba.construaxis.backend.parceiro.service.FornecedorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fornecedores")
public class FornecedorController {

    private final FornecedorService fornecedorService;

    public FornecedorController(FornecedorService fornecedorService) {
        this.fornecedorService = fornecedorService;
    }

    @PostMapping("/cadastrar-cnpj/{cnpj}")
    public ResponseEntity<Fornecedor> cadastrar(@PathVariable String cnpj) {
        Fornecedor fornecedor = fornecedorService.cadastrarPorCnpj(cnpj);
        return ResponseEntity.status(HttpStatus.CREATED).body(fornecedor);
    }
}