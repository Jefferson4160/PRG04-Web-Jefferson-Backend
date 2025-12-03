package br.com.ifba.construaxis.backend.estoque.controller;

import br.com.ifba.construaxis.backend.estoque.dto.EntradaGetResponseDTO;
import br.com.ifba.construaxis.backend.estoque.dto.EntradaPostRequestDTO;
import br.com.ifba.construaxis.backend.estoque.entity.Entrada;
import br.com.ifba.construaxis.backend.estoque.mapper.EntradaMapper;
import br.com.ifba.construaxis.backend.estoque.service.EntradaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/entradas")
public class EntradaController {

    private final EntradaService entradaService;
    private final EntradaMapper entradaMapper;

    public EntradaController(EntradaService entradaService, EntradaMapper entradaMapper) {
        this.entradaService = entradaService;
        this.entradaMapper = entradaMapper;
    }

    @PostMapping
    public ResponseEntity<?> registrarEntrada(@RequestBody EntradaPostRequestDTO dto) {
        try {
            Entrada entrada = entradaMapper.toEntity(dto);
            Entrada novaEntrada = entradaService.registrarEntrada(entrada);
            EntradaGetResponseDTO response = entradaMapper.toDTO(novaEntrada);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interno: " + e.getMessage());
        }
    }
}
