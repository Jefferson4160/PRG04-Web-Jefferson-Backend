package br.com.ifba.construaxis.backend.estoque.controller;

import br.com.ifba.construaxis.backend.estoque.entity.UnidadeMedida;
import br.com.ifba.construaxis.backend.estoque.service.UnidadeMedidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/unidades-medida")
@CrossOrigin(origins = {"http://localhost:3000", "https://construaxis.netlify.app"})
public class UnidadeMedidaController {

    @Autowired
    private UnidadeMedidaService unidadeService;

    @GetMapping("/findall")
    public ResponseEntity<List<UnidadeMedida>> findAll() {
        return ResponseEntity.ok(unidadeService.findAll());
    }

    @PostMapping
    public ResponseEntity<UnidadeMedida> save(@RequestBody UnidadeMedida unidade) {
        return ResponseEntity.ok(unidadeService.save(unidade));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UnidadeMedida> update(@PathVariable UUID id, @RequestBody Map<String, String> body) {
        return ResponseEntity.ok(unidadeService.update(id, body.get("nome")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        unidadeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}