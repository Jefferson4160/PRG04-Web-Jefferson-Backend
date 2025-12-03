package br.com.ifba.construaxis.backend.estoque.entity;


import br.com.ifba.construaxis.backend.infraestructure.entity.PersistenceEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "entradas")
@Data
@NoArgsConstructor
public class Entrada extends PersistenceEntity {

    @Column(name = "data_entrada")
    private LocalDate dataEntrada;

    @Column(name = "item_id")
    private UUID itemId;

    @Column(name = "fornecedor_id")
    private UUID fornecedorId;

    @Column(name = "nota_fiscal_ra")
    private String notaFiscalRa;

    @Column(name = "quantidade_nf")
    private Double quantidadeNf;

    @Column(name = "valor_total")
    private Double valorTotal;

    @Column(name = "quantidade_entrada")
    private Double quantidadeEntrada;

    @Column(name = "valor_unitario")
    private Double valorUnitario;

    @Column(name = "almoxarife_id")
    private UUID almoxarifeId;
}
