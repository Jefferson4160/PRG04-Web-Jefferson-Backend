package br.com.ifba.construaxis.backend.estoque.controller;

import br.com.ifba.construaxis.backend.estoque.dto.ItemPostRequestDTO;
import br.com.ifba.construaxis.backend.estoque.entity.Item;
import br.com.ifba.construaxis.backend.estoque.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itens") 
@CrossOrigin(origins = {"http://localhost:3000", "https://construaxis.netlify.app"}) // Garante que o React consiga acessar
public class ItemController {

    @Autowired
    private ItemService itemService;

    // Rota para buscar todos os itens
    @GetMapping("/findall")
    public ResponseEntity<List<Item>> findAll() {
        return ResponseEntity.ok(itemService.findAll());
    }
    
    @PostMapping
    public ResponseEntity<Item> cadastrar(@RequestBody ItemPostRequestDTO dto) {
        Item novoItem = itemService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoItem);
    }
}