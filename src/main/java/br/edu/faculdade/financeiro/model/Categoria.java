package br.edu.faculdade.financeiro.model;

import jakarta.persistence.*;

@Entity
@Table(name = "categorias")
public class Categoria {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable = false, length = 80) private String nome;
    @Enumerated(EnumType.STRING) @Column(nullable = false, length = 10) private TipoLancamento tipo;
    protected Categoria() {}
    public Categoria(String nome, TipoLancamento tipo) { atualizar(nome, tipo); }
    public void atualizar(String nome, TipoLancamento tipo) { this.nome = nome; this.tipo = tipo; }
    public Long getId() { return id; }
    public String getNome() { return nome; }
    public TipoLancamento getTipo() { return tipo; }
}
