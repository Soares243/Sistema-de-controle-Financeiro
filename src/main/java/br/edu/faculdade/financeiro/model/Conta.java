package br.edu.faculdade.financeiro.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "contas")
public class Conta {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable = false, length = 100) private String nome;
    @Column(nullable = false, precision = 15, scale = 2) private BigDecimal saldoInicial;
    @ManyToOne(fetch = FetchType.LAZY, optional = false) @JoinColumn(name = "usuario_id", nullable = false) private Usuario usuario;
    protected Conta() {}
    public Conta(String nome, BigDecimal saldoInicial, Usuario usuario) { atualizar(nome, saldoInicial, usuario); }
    public void atualizar(String nome, BigDecimal saldoInicial, Usuario usuario) { this.nome = nome; this.saldoInicial = saldoInicial; this.usuario = usuario; }
    public Long getId() { return id; }
    public String getNome() { return nome; }
    public BigDecimal getSaldoInicial() { return saldoInicial; }
    public Usuario getUsuario() { return usuario; }
}
