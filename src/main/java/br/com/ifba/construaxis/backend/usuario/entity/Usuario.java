package br.com.ifba.construaxis.backend.usuario.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.ifba.construaxis.backend.infrastructure.entity.PersistenceEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios") // Tabela correta para os dados de acesso
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario extends PersistenceEntity {

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;

    /**
     * Relacionamento 1:1 com Pessoa (Nome, Documento, etc.)
     * Conforme planejado no Diagrama de Identidade.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pessoa_id", referencedColumnName = "id")
    private Pessoa pessoa;

    /**
     * Relacionamento N:1 com Perfil (ADMIN, ESTOQUISTA, etc.)
     * Permite que você mude as permissões sem alterar o código.
     */
    @ManyToOne
    @JoinColumn(name = "perfil_id", nullable = false)
    private Perfil perfil;
}