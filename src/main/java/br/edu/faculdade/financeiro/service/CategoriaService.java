package br.edu.faculdade.financeiro.service;
import br.edu.faculdade.financeiro.dto.*;
import br.edu.faculdade.financeiro.error.*;
import br.edu.faculdade.financeiro.model.Categoria;
import br.edu.faculdade.financeiro.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class CategoriaService {
    private final CategoriaRepository categorias; private final LancamentoRepository lancamentos;
    public CategoriaService(CategoriaRepository categorias, LancamentoRepository lancamentos) {
        this.categorias=categorias; this.lancamentos=lancamentos;
    }
    public Categoria obterEntidade(Long id) { return categorias.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Categoria", id)); }
    @Transactional public CategoriaResponse criar(CategoriaRequest r) { return CategoriaResponse.de(categorias.save(new Categoria(r.nome().trim(), r.tipo()))); }
    @Transactional(readOnly=true) public CategoriaResponse obter(Long id) { return CategoriaResponse.de(obterEntidade(id)); }
    @Transactional(readOnly=true) public List<CategoriaResponse> listar() { return categorias.findAll().stream().map(CategoriaResponse::de).toList(); }
    @Transactional public CategoriaResponse atualizar(Long id, CategoriaRequest r) {
        Categoria c=obterEntidade(id);
        if (lancamentos.existsByCategoriaId(id) && c.getTipo()!=r.tipo())
            throw new RegraNegocioException("Não é possível mudar o tipo de categoria com lançamentos.");
        c.atualizar(r.nome().trim(), r.tipo());
        return CategoriaResponse.de(c);
    }
    @Transactional public void excluir(Long id) {
        obterEntidade(id);
        if (lancamentos.existsByCategoriaId(id)) throw new RegraNegocioException("Exclua os lançamentos da categoria primeiro.");
        categorias.deleteById(id);
    }
}
