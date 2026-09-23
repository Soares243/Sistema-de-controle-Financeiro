package br.edu.faculdade.financeiro.dto;
import br.edu.faculdade.financeiro.model.TipoLancamento;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
public record LancamentoRequest(@NotBlank @Size(max=180) String descricao,
    @NotNull @DecimalMin("0.01") @Digits(integer=13, fraction=2) BigDecimal valor,
    @NotNull LocalDate data, @NotNull TipoLancamento tipo, @NotNull Long contaId, @NotNull Long categoriaId) {}
