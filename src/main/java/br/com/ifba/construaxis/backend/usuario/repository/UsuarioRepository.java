package br.com.ifba.construaxis.backend.usuario.repository;

import br.com.ifba.construaxis.backend.usuario.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;
import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    // Busca o nome dentro da entidade Pessoa vinculada ao Usuario
    List<Usuario> findByPessoaNomeContainingIgnoreCase(String nome);
    // Busca pelo campo login
    Optional<Usuario> findByLogin(String login);
}
