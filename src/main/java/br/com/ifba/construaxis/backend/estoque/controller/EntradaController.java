package br.com.ifba.construaxis.backend.estoque.controller;

import br.com.ifba.construaxis.backend.estoque.dto.EntradaGetResponseDTO;
import br.com.ifba.construaxis.backend.estoque.dto.EntradaPostRequestDTO;
import br.com.ifba.construaxis.backend.estoque.entity.Entrada;
import br.com.ifba.construaxis.backend.estoque.mapper.EntradaMapper;
import br.com.ifba.construaxis.backend.estoque.service.EntradaService;
import jakarta.validation.Valid;
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
    public ResponseEntity<EntradaGetResponseDTO> registrarEntrada(
            @Valid @RequestBody EntradaPostRequestDTO dto) {

        Entrada entrada = entradaMapper.toEntity(dto);
        Entrada novaEntrada = entradaService.registrarEntrada(entrada);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(entradaMapper.toDTO(novaEntrada));
    }
}
