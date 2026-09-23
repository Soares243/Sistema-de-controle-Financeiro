package br.edu.faculdade.financeiro.dto;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
public record ContaRequest(@NotBlank @Size(max=100) String nome, @NotNull @Digits(integer=13, fraction=2) BigDecimal saldoInicial, @NotNull Long usuarioId) {}
