package br.com.ifba.construaxis.backend.usuario.service;

import br.com.ifba.construaxis.backend.usuario.entity.Usuario;
import br.com.ifba.construaxis.backend.usuario.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    // Injeção de Dependência
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // Corresponde ao findByAll do slide (lista todos)
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    // Busca por Nome Completo (simulando uma busca flexível)
    public List<Usuario> findByNome(String nome) {
        return usuarioRepository.findByNomeCompletoContainingIgnoreCase(nome);
    }
}
