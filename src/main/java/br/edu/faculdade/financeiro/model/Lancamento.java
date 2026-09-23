package br.edu.faculdade.financeiro.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "lancamentos")
public class Lancamento {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable = false, length = 180) private String descricao;
    @Column(nullable = false, precision = 15, scale = 2) private BigDecimal valor;
    @Column(nullable = false) private LocalDate data;
    @Enumerated(EnumType.STRING) @Column(nullable = false, length = 10) private TipoLancamento tipo;
    @ManyToOne(fetch = FetchType.LAZY, optional = false) @JoinColumn(name = "conta_id", nullable = false) private Conta conta;
    @ManyToOne(fetch = FetchType.LAZY, optional = false) @JoinColumn(name = "categoria_id", nullable = false) private Categoria categoria;
    protected Lancamento() {}
    public Lancamento(String descricao, BigDecimal valor, LocalDate data, TipoLancamento tipo, Conta conta, Categoria categoria) {
        atualizar(descricao, valor, data, tipo, conta, categoria);
    }
    public void atualizar(String descricao, BigDecimal valor, LocalDate data, TipoLancamento tipo, Conta conta, Categoria categoria) {
        this.descricao = descricao; this.valor = valor; this.data = data; this.tipo = tipo; this.conta = conta; this.categoria = categoria;
    }
    public Long getId() { return id; }
    public String getDescricao() { return descricao; }
    public BigDecimal getValor() { return valor; }
    public LocalDate getData() { return data; }
    public TipoLancamento getTipo() { return tipo; }
    public Conta getConta() { return conta; }
    public Categoria getCategoria() { return categoria; }
}
