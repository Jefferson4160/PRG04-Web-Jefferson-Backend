package br.com.ifba.construaxis.backend.usuario.repository;

import br.com.ifba.construaxis.backend.usuario.entity.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;
import java.util.Optional;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, UUID> {
    Optional<Perfil> findByNome(String nome);
}