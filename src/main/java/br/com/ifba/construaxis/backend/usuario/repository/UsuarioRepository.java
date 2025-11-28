package br.com.ifba.construaxis.backend.usuario.repository;

import br.com.ifba.construaxis.backend.usuario.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;
import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    //Método que o Spring JPA implementa automaticamente (busca por nome)
    List<Usuario> findByNomeCompletoContainingIgnoreCase(String nome);
}
