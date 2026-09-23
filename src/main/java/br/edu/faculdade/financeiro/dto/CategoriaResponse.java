package br.edu.faculdade.financeiro.dto;
import br.edu.faculdade.financeiro.model.*;
public record CategoriaResponse(Long id, String nome, TipoLancamento tipo) {
    public static CategoriaResponse de(Categoria c) { return new CategoriaResponse(c.getId(), c.getNome(), c.getTipo()); }
}
