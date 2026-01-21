package br.com.ifba.construaxis.backend.estoque.entity;

import br.com.ifba.construaxis.backend.infrastructure.entity.PersistenceEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "itens")
public class Item extends PersistenceEntity {

    @Column(nullable = false)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "unidade_id", nullable = false)
    private UnidadeMedida unidadeMedida;

    @Column(name = "codigo", insertable = false, updatable = false)
    private Integer codigo;

    // Construtor padrão
    public Item() {}

    // GETTERS E SETTERS MANUAIS (Para resolver o erro do VS Code)
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }

    public UnidadeMedida getUnidadeMedida() { return unidadeMedida; }
    public void setUnidadeMedida(UnidadeMedida unidadeMedida) { this.unidadeMedida = unidadeMedida; }

    public Integer getCodigo() { return codigo; }
}