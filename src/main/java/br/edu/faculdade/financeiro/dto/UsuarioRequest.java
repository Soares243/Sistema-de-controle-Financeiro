package br.edu.faculdade.financeiro.dto;
import jakarta.validation.constraints.*;
public record UsuarioRequest(
    @NotBlank @Size(max=120) String nome,
    @NotBlank @Email @Size(max=160) String email,
    @NotBlank @Size(min=8, max=72) String senha
) {}
