package br.com.ifba.construaxis.backend.estoque.dto;

import java.time.LocalDateTime;

public class EntradaGetResponseDTO {
    private String itemDescricao;
    private Double quantidade;
    private LocalDateTime data;
    private String nf;

    // Getters e Setters
    public String getItemDescricao() { return itemDescricao; }
    public void setItemDescricao(String itemDescricao) { this.itemDescricao = itemDescricao; }

    public Double getQuantidade() { return quantidade; }
    public void setQuantidade(Double quantidade) { this.quantidade = quantidade; }

    public LocalDateTime getData() { return data; }
    public void setData(LocalDateTime data) { this.data = data; }

    public String getNf() { return nf; }
    public void setNf(String nf) { this.nf = nf; }
}