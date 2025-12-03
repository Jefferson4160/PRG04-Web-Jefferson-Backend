package br.com.ifba.construaxis.backend.estoque.entity;

// Importa a entidade base que está na pasta irmã
import br.com.ifba.construaxis.backend.infraestructure.entity.PersistenceEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "itens")
@Data
@NoArgsConstructor
public class Item extends PersistenceEntity {

    @Column(name = "codigo")
    private Long codigo;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "unidade_medida")
    private String unidadeMedida;

    @Column(name = "saldo_atual")
    private Double saldoAtual;

    @Column(name = "preco_unitario_medio")
    private Double precoUnitarioMedio;
}