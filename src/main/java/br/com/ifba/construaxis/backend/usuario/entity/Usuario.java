package br.com.ifba.construaxis.backend.usuario.entity;

import br.com.ifba.construaxis.backend.infraestructure.entity.PersistenceEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "perfis") // Mapeia para a tabela 'perfis'
@Data //Adiciona os getters, setters
@AllArgsConstructor
@NoArgsConstructor
public class Usuario extends PersistenceEntity {

    @Column(name = "nome_completo")
    private String nomeCompleto;

    @Column(name = "funcao")
    private String funcao; // 'admin' ou 'operador'

    // Construtores, Getters e Setters (omitidos para brevidade, mas são essenciais)
    // Se estiver usando Lombok, adicione @Data, @NoArgsConstructor, @AllArgsConstructor
}