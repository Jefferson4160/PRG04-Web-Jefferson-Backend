package br.com.ifba.construaxis.backend.usuario.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.ifba.construaxis.backend.infrastructure.entity.PersistenceEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pessoas") // Tabela para os dados civis (Nome, Documento)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pessoa extends PersistenceEntity {

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    @JsonProperty("cpf")
    private String documento; 
}