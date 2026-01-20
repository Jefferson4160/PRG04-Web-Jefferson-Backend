package br.com.ifba.construaxis.backend.parceiro.entity;

import br.com.ifba.construaxis.backend.infrastructure.entity.PersistenceEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "fornecedores")
@Data
@NoArgsConstructor
public class Fornecedor extends PersistenceEntity {

    @Column(name = "nome_fantasia", nullable = false, unique = true)
    private String nomeFantasia;

    @Column(name = "cnpj", unique = true)
    private String cnpj;
}