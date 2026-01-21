package br.com.ifba.construaxis.backend.estoque.entity;

import br.com.ifba.construaxis.backend.infrastructure.entity.PersistenceEntity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "entradas")
public class Entrada extends PersistenceEntity {

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Column(nullable = false)
    private Double quantidade;

    @Column(name = "data_entrada", nullable = false)
    private LocalDateTime dataEntrada;

    @Column(name = "numero_nota_fiscal")
    private String numeroNotaFiscal;

    // Construtor padrão
    public Entrada() {
        this.dataEntrada = LocalDateTime.now();
    }

    // Getters e Setters Manuais (Para evitar erros no VS Code)
    public Item getItem() { return item; }
    public void setItem(Item item) { this.item = item; }

    public Double getQuantidade() { return quantidade; }
    public void setQuantidade(Double quantidade) { this.quantidade = quantidade; }

    public LocalDateTime getDataEntrada() { return dataEntrada; }
    public void setDataEntrada(LocalDateTime dataEntrada) { this.dataEntrada = dataEntrada; }

    public String getNumeroNotaFiscal() { return numeroNotaFiscal; }
    public void setNumeroNotaFiscal(String numeroNotaFiscal) { this.numeroNotaFiscal = numeroNotaFiscal; }
}