package br.com.ifba.construaxis.backend.usuario.entity;

import br.com.ifba.construaxis.backend.infrastructure.entity.PersistenceEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "perfis")
@Data
@NoArgsConstructor
public class Perfil extends PersistenceEntity {

    @Column(name = "nome", nullable = false, unique = true)
    private String nome; // "ADMIN", "ESTOQUISTA", "COMPRADOR", etc.
}