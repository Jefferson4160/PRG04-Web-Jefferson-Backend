package br.com.ifba.construaxis.backend.usuario.service;

import br.com.ifba.construaxis.backend.usuario.entity.Pessoa;
import br.com.ifba.construaxis.backend.usuario.entity.Usuario;
import br.com.ifba.construaxis.backend.usuario.entity.Perfil;
import br.com.ifba.construaxis.backend.usuario.repository.UsuarioRepository;
import br.com.ifba.construaxis.backend.usuario.repository.PerfilRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    @Autowired
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

    public Usuario verificarLogin(String login, String senha) {
    // Busca o usuário ou lança erro se não existir
    Usuario usuario = usuarioRepository.findByLogin(login)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

    // Compara a senha (como não usamos BCrypt ainda, é comparação direta)
    if (!usuario.getSenha().equals(senha)) {
        throw new RuntimeException("Senha incorreta!");
    }

    return usuario;
    }

    //impede de excluir o proprio usuario
    @Transactional
    public void delete(UUID idParaExcluir, UUID idLogado) {
        // 🛡️ A grande trava:
        if (idParaExcluir.equals(idLogado)) {
            throw new RuntimeException("Operação negada: Você não pode excluir sua própria conta enquanto estiver logado.");
        }
        usuarioRepository.deleteById(idParaExcluir);
    }

    @Transactional
    public Usuario update(UUID id, Map<String, Object> dados) {
        // 1. Busca o usuário ou lança erro se não existir
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Operador não encontrado."));

        // 2. Atualiza dados da Pessoa (Nome e Documento/CPF)
        if (dados.containsKey("nome")) usuario.getPessoa().setNome((String) dados.get("nome"));
        if (dados.containsKey("documento")) usuario.getPessoa().setDocumento((String) dados.get("documento"));

        // 3. Atualiza Login
        if (dados.containsKey("login")) usuario.setLogin((String) dados.get("login"));

        // 4. Usando o ID (UUID) que vem do React
        if (dados.containsKey("perfilId")) {
            String idString = (String) dados.get("perfilId");
            if (idString != null && !idString.isEmpty()) {
                // Transformamos o ID que veio do <select> em UUID real
                UUID pId = UUID.fromString(idString);
                Perfil novoPerfil = perfilRepository.findById(pId)
                    .orElseThrow(() -> new RuntimeException("Perfil não encontrado com o ID: " + pId));
                
                usuario.setPerfil(novoPerfil); // Agora o Hibernate vai disparar o UPDATE
            }
        }

        // 5. Atualiza Senha 
        String novaSenha = (String) dados.get("senha");
        if (novaSenha != null && !novaSenha.isEmpty()) {
            usuario.setSenha(novaSenha);
        }

        return usuarioRepository.saveAndFlush(usuario);
    }
}