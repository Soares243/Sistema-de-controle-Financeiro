package br.edu.faculdade.financeiro.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuarios", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Usuario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable = false, length = 120) private String nome;
    @Column(nullable = false, length = 160) private String email;
    @OneToMany(mappedBy = "usuario") private List<Conta> contas = new ArrayList<>();
    protected Usuario() {}
    public Usuario(String nome, String email) { this.nome = nome; this.email = email; }
    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public void atualizar(String nome, String email) { this.nome = nome; this.email = email; }
}
