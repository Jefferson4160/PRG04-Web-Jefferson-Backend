package br.com.ifba.construaxis.backend.estoque.dto;

import java.util.UUID;

public class ItemPostRequestDTO {
    private String descricao;
    private UUID categoriaId;
    private UUID unidadeMedidaId;
    private UUID criadoPorId; 

    public ItemPostRequestDTO() {}

    // Getters e Setters
    public String getDescricao() { return descricao; }
    public void setDescricao(String d) { this.descricao = d; }

    public UUID getCategoriaId() { return categoriaId; }
    public void setCategoriaId(UUID id) { this.categoriaId = id; }

    public UUID getUnidadeMedidaId() { return unidadeMedidaId; }
    public void setUnidadeMedidaId(UUID id) { this.unidadeMedidaId = id; }

    public UUID getCriadoPorId() { return criadoPorId; }
    public void setCriadoPorId(UUID id) { this.criadoPorId = id; }
}