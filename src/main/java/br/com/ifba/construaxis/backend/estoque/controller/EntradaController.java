package br.com.ifba.construaxis.backend.estoque.controller;

import br.com.ifba.construaxis.backend.estoque.dto.EntradaGetResponseDTO;
import br.com.ifba.construaxis.backend.estoque.dto.EntradaPostRequestDTO;
import br.com.ifba.construaxis.backend.estoque.service.EntradaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entradas")
public class EntradaController {

    @Autowired
    private EntradaService entradaService;

    /**
     * Endpoint para registrar uma nova chegada de material (POST)
     */
    @PostMapping
    public ResponseEntity<EntradaGetResponseDTO> registrar(@RequestBody EntradaPostRequestDTO dto) {
        EntradaGetResponseDTO novaEntrada = entradaService.registrarEntrada(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaEntrada);
    }

    /**
     * Endpoint para listar todo o histórico de entradas (GET)
     */
    @GetMapping("/findall")
    public ResponseEntity<List<EntradaGetResponseDTO>> findAll() {
        return ResponseEntity.ok(entradaService.findAll());
    }
}