package br.edu.faculdade.financeiro.dto;
import br.edu.faculdade.financeiro.model.TipoLancamento;
import jakarta.validation.constraints.*;
public record CategoriaRequest(@NotBlank @Size(max=80) String nome, @NotNull TipoLancamento tipo) {}
