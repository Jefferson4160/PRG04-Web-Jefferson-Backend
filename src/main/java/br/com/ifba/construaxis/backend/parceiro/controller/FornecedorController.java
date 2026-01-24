package br.com.ifba.construaxis.backend.parceiro.controller;

import br.com.ifba.construaxis.backend.parceiro.entity.Fornecedor;
import br.com.ifba.construaxis.backend.parceiro.service.FornecedorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/fornecedores")
@CrossOrigin(origins = {"http://localhost:3000", "https://construaxis.netlify.app"})
public class FornecedorController {

    private final FornecedorService fornecedorService;

    public FornecedorController(FornecedorService fornecedorService) {
        this.fornecedorService = fornecedorService;
    }

    @GetMapping
    public ResponseEntity<List<Fornecedor>> findAll() {
        // O ResponseEntity.ok envia o status 200 para o React
        return ResponseEntity.ok(fornecedorService.findAll()); 
    }

    @PostMapping("/cadastrar-cnpj/{cnpj}")
    public ResponseEntity<Fornecedor> cadastrar(@PathVariable String cnpj) {
        Fornecedor fornecedor = fornecedorService.cadastrarPorCnpj(cnpj);
        return ResponseEntity.status(HttpStatus.CREATED).body(fornecedor);
    }

    @PostMapping
    public ResponseEntity<Fornecedor> save(@RequestBody Fornecedor fornecedor) {
        
        return ResponseEntity.status(HttpStatus.CREATED).body(fornecedorService.save(fornecedor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable UUID id) {
        fornecedorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}