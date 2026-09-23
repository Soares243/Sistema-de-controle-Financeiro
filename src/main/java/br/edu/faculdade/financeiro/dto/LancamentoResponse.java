package br.edu.faculdade.financeiro.dto;
import br.edu.faculdade.financeiro.model.*;
import java.math.BigDecimal;
import java.time.LocalDate;
public record LancamentoResponse(Long id, String descricao, BigDecimal valor, LocalDate data,
    TipoLancamento tipo, Long contaId, Long categoriaId) {
    public static LancamentoResponse de(Lancamento l) {
        return new LancamentoResponse(l.getId(), l.getDescricao(), l.getValor(), l.getData(), l.getTipo(), l.getConta().getId(), l.getCategoria().getId());
    }
}
