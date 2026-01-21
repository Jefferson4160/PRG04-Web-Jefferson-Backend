package br.com.ifba.construaxis.backend.estoque.dto;

import java.util.UUID;

public class ItemPostRequestDTO {

    private String descricao;
    private UUID categoriaId;
    private UUID unidadeMedidaId;

    // Construtor padrão
    public ItemPostRequestDTO() {}

    // Getters - 
    public String getDescricao() {
        return descricao;
    }

    public UUID getCategoriaId() {
        return categoriaId;
    }

    public UUID getUnidadeMedidaId() {
        return unidadeMedidaId;
    }

    // Setters
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setCategoriaId(UUID categoriaId) {
        this.categoriaId = categoriaId;
    }

    public void setUnidadeMedidaId(UUID unidadeMedidaId) {
        this.unidadeMedidaId = unidadeMedidaId;
    }
}