package br.edu.faculdade.financeiro.service;
import br.edu.faculdade.financeiro.dto.*;
import br.edu.faculdade.financeiro.error.*;
import br.edu.faculdade.financeiro.model.*;
import br.edu.faculdade.financeiro.repository.LancamentoRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LancamentoService {
    private final LancamentoRepository lancamentos; private final ContaService contas; private final CategoriaService categorias;
    public LancamentoService(LancamentoRepository lancamentos, ContaService contas, CategoriaService categorias) {
        this.lancamentos=lancamentos; this.contas=contas; this.categorias=categorias;
    }
    private Lancamento entidade(Long id) { return lancamentos.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Lançamento", id)); }
    private Categoria categoriaValida(LancamentoRequest r) {
        Categoria c=categorias.obterEntidade(r.categoriaId());
        if (c.getTipo()!=r.tipo()) throw new RegraNegocioException("O tipo do lançamento deve ser igual ao da categoria.");
        return c;
    }
    @Transactional public LancamentoResponse criar(LancamentoRequest r) {
        Categoria c=categoriaValida(r); Conta conta=contas.obterEntidade(r.contaId());
        return LancamentoResponse.de(lancamentos.save(new Lancamento(r.descricao().trim(),r.valor(),r.data(),r.tipo(),conta,c)));
    }
    @Transactional(readOnly=true) public LancamentoResponse obter(Long id) { return LancamentoResponse.de(entidade(id)); }
    @Transactional(readOnly=true) public Page<LancamentoResponse> listar(Long contaId, Pageable pagina) {
        return (contaId==null ? lancamentos.findAll(pagina) : lancamentos.findByContaId(contaId,pagina)).map(LancamentoResponse::de);
    }
    @Transactional public LancamentoResponse atualizar(Long id, LancamentoRequest r) {
        Lancamento l=entidade(id); Categoria c=categoriaValida(r); Conta conta=contas.obterEntidade(r.contaId());
        l.atualizar(r.descricao().trim(),r.valor(),r.data(),r.tipo(),conta,c);
        return LancamentoResponse.de(l);
    }
    @Transactional public void excluir(Long id) { lancamentos.delete(entidade(id)); }
}
