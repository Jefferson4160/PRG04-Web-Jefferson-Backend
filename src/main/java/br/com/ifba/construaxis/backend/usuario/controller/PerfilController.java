package br.com.ifba.construaxis.backend.usuario.controller;

import br.com.ifba.construaxis.backend.usuario.entity.Perfil;
import br.com.ifba.construaxis.backend.usuario.repository.PerfilRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/perfis")
public class PerfilController {

    private final PerfilRepository perfilRepository;

    public PerfilController(PerfilRepository perfilRepository) {
        this.perfilRepository = perfilRepository;
    }

    

    // --- POST: Criar um novo perfil ---
    @PostMapping
    public ResponseEntity<Perfil> criar(@RequestBody Perfil perfil) {
        // Como herda de PersistenceEntity, o ID UUID será gerado no save
        Perfil novoPerfil = perfilRepository.save(perfil);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPerfil);
    }

    // --- GET: Listar todos os perfis ---
    @GetMapping
    public ResponseEntity<List<Perfil>> listarTodos() {
        return ResponseEntity.ok(perfilRepository.findAll());
    }

    // --- GET: Buscar perfil por ID ---
    @GetMapping("/{id}")
    public ResponseEntity<Perfil> buscarPorId(@PathVariable UUID id) {
        return perfilRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // --- DELETE: Remover um perfil ---
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        if (!perfilRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        perfilRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}