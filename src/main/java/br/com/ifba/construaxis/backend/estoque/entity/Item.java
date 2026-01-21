package br.com.ifba.construaxis.backend.estoque.entity;

import br.com.ifba.construaxis.backend.infrastructure.entity.PersistenceEntity;
import br.com.ifba.construaxis.backend.usuario.entity.Usuario; 
import jakarta.persistence.*;

@Entity
@Table(name = "itens")
public class Item extends PersistenceEntity {

    @Column(nullable = false)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "categoria", nullable = false)
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "unidade_medida", nullable = false)
    private UnidadeMedida unidadeMedida;

    @ManyToOne
    @JoinColumn(name = "criado_por_id", nullable = false) 
    private Usuario criadoPor;

    @Column(name = "codigo", insertable = false, updatable = false)
    private Integer codigo;

    private double saldoAtual;
    private double precoUnitarioMedio;

    public Item() {}

    // Getters e Setters (Mantendo os manuais para evitar erros no VS Code)
    public String getDescricao() { return descricao; }
    public void setDescricao(String d) { this.descricao = d; }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria c) { this.categoria = c; }

    public UnidadeMedida getUnidadeMedida() { return unidadeMedida; }
    public void setUnidadeMedida(UnidadeMedida u) { this.unidadeMedida = u; }

    public Usuario getCriadoPor() { return criadoPor; }
    public void setCriadoPor(Usuario u) { this.criadoPor = u; }

    public Integer getCodigo() { return codigo; }
    public double getSaldoAtual() { return saldoAtual; }
    public void setSaldoAtual(double s) { this.saldoAtual = s; }
    public double getPrecoUnitarioMedio() { return precoUnitarioMedio; }
    public void setPrecoUnitarioMedio(double p) { this.precoUnitarioMedio = p; }
}