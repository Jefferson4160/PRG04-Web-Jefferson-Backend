package br.com.ifba.construaxis.backend.usuario.controller;

import br.com.ifba.construaxis.backend.usuario.entity.Usuario;
import br.com.ifba.construaxis.backend.usuario.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController // Define a classe como API REST
@RequestMapping(path = "/usuarios") // Prefixo da URL: /usuarios
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Endpoint: GET /usuarios/findall
    @GetMapping(path = "/findall", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Usuario>> findAll() {
        // Chama a regra de negócio (Service)
        List<Usuario> usuarios = usuarioService.findAll();

        // Retorna a resposta HTTP (Status 200 OK e o JSON no corpo)
        return ResponseEntity.status(HttpStatus.OK).body(usuarios);
    }

    // Endpoint: GET /usuarios/buscar?nome={termo}
    @GetMapping(path = "/buscar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam String nome) {
        List<Usuario> usuarios = usuarioService.findByNome(nome);
        return ResponseEntity.status(HttpStatus.OK).body(usuarios);
    }

}
