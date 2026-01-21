package br.com.ifba.construaxis.backend.estoque.dto;

import java.util.UUID;

public class EntradaPostRequestDTO {
    private UUID itemId;
    private Double quantidade;
    private String numeroNotaFiscal;

    // Getters e Setters
    public UUID getItemId() { return itemId; }
    public void setItemId(UUID itemId) { this.itemId = itemId; }

    public Double getQuantidade() { return quantidade; }
    public void setQuantidade(Double quantidade) { this.quantidade = quantidade; }

    public String getNumeroNotaFiscal() { return numeroNotaFiscal; }
    public void setNumeroNotaFiscal(String numeroNotaFiscal) { this.numeroNotaFiscal = numeroNotaFiscal; }
}