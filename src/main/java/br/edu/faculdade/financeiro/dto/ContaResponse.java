package br.edu.faculdade.financeiro.dto;
import br.edu.faculdade.financeiro.model.Conta;
import java.math.BigDecimal;
public record ContaResponse(Long id, String nome, BigDecimal saldoInicial, Long usuarioId) {
    public static ContaResponse de(Conta c) { return new ContaResponse(c.getId(), c.getNome(), c.getSaldoInicial(), c.getUsuario().getId()); }
}
