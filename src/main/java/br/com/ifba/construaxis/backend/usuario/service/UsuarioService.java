package br.com.ifba.construaxis.backend.usuario.service;

import br.com.ifba.construaxis.backend.usuario.entity.Pessoa;
import br.com.ifba.construaxis.backend.usuario.entity.Usuario;
import br.com.ifba.construaxis.backend.usuario.entity.Perfil;
import br.com.ifba.construaxis.backend.usuario.repository.UsuarioRepository;
import br.com.ifba.construaxis.backend.usuario.repository.PerfilRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PerfilRepository perfilRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, PerfilRepository perfilRepository) {
        this.usuarioRepository = usuarioRepository;
        this.perfilRepository = perfilRepository;
    }

    // --- MÉTODO DE CADASTRO (TASK 2 e 4) ---
    @Transactional
    public Usuario cadastrarUsuario(Usuario usuario, Pessoa pessoa, UUID perfilId) {
        Perfil perfil = perfilRepository.findById(perfilId)
                .orElseThrow(() -> new RuntimeException("Perfil não encontrado!"));

        usuario.setPessoa(pessoa);
        usuario.setPerfil(perfil);

        return usuarioRepository.save(usuario);
    }
    
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public List<Usuario> findByNome(String nome) {
        return usuarioRepository.findByPessoaNomeContainingIgnoreCase(nome);
    }
}