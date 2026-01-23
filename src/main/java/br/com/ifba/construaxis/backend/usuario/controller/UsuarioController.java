package br.com.ifba.construaxis.backend.usuario.controller;

import br.com.ifba.construaxis.backend.usuario.dto.UsuarioPostRequestDTO;
import br.com.ifba.construaxis.backend.usuario.entity.Pessoa;
import br.com.ifba.construaxis.backend.usuario.entity.Usuario;
import br.com.ifba.construaxis.backend.usuario.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController // Define a classe como API REST
@RequestMapping(path = "/usuarios") // Prefixo da URL: /usuarios
@CrossOrigin(origins = {"http://localhost:3000", "https://construaxis.netlify.app"})
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

    @PostMapping
    public ResponseEntity<Usuario> cadastrar(@RequestBody UsuarioPostRequestDTO dto) {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(dto.getNome());
        pessoa.setDocumento(dto.getDocumento());

        Usuario usuario = new Usuario();
        usuario.setLogin(dto.getLogin());
        usuario.setSenha(dto.getSenha());

        // Chama o service que ajustamos para lidar com Pessoa + Usuario + Perfil
        Usuario novoUsuario = usuarioService.cadastrarUsuario(usuario, pessoa, dto.getPerfilId());

        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody Map<String, String> credenciais) {
        String login = credenciais.get("login");
        String senha = credenciais.get("senha");

        Usuario usuario = usuarioService.verificarLogin(login, senha);
        return ResponseEntity.ok(usuario);
    }
}
