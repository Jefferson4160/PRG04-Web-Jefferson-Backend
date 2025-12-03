package br.com.ifba.construaxis.backend.estoque.controller;

import br.com.ifba.construaxis.backend.estoque.entity.Entrada;
import br.com.ifba.construaxis.backend.estoque.service.EntradaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/entradas") // Prefixo da URL: /entradas
public class EntradaController {

    private final EntradaService entradaService;

    public EntradaController(EntradaService entradaService) {
        this.entradaService = entradaService;
    }

    // Endpoint: POST /entradas
    // Recebe o JSON do React com os dados da entrada
    @PostMapping
    public ResponseEntity<?> registrarEntrada(@RequestBody Entrada entrada) {
        try {
            Entrada novaEntrada = entradaService.registrarEntrada(entrada);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaEntrada);
        } catch (IllegalArgumentException e) {
            // 400 Bad Request
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (RuntimeException e) {
            // 500 Internal Server Error (Erro no BD, Item não encontrado, etc.)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno ao processar a entrada: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro desconhecido.");
        }
    }
}