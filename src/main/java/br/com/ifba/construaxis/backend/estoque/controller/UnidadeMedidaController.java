package br.com.ifba.construaxis.backend.estoque.controller;

import br.com.ifba.construaxis.backend.estoque.entity.UnidadeMedida;
import br.com.ifba.construaxis.backend.estoque.service.UnidadeMedidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/unidades-medida")
public class UnidadeMedidaController {

    @Autowired
    private UnidadeMedidaService unidadeService;

    @GetMapping("/findall")
    public ResponseEntity<List<UnidadeMedida>> findAll() {
        List<UnidadeMedida> unidades = unidadeService.findAll();
        return ResponseEntity.ok(unidades);
    }
}