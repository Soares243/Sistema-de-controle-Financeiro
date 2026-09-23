package br.edu.faculdade.financeiro.service;
import br.edu.faculdade.financeiro.dto.*;
import br.edu.faculdade.financeiro.error.*;
import br.edu.faculdade.financeiro.model.Conta;
import br.edu.faculdade.financeiro.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ContaService {
    private final ContaRepository contas; private final UsuarioService usuarios; private final LancamentoRepository lancamentos;
    public ContaService(ContaRepository contas, UsuarioService usuarios, LancamentoRepository lancamentos) {
        this.contas=contas; this.usuarios=usuarios; this.lancamentos=lancamentos;
    }
    public Conta obterEntidade(Long id) { return contas.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Conta", id)); }
    @Transactional public ContaResponse criar(ContaRequest r) {
        return ContaResponse.de(contas.save(new Conta(r.nome().trim(), r.saldoInicial(), usuarios.obterEntidade(r.usuarioId()))));
    }
    @Transactional(readOnly=true) public ContaResponse obter(Long id) { return ContaResponse.de(obterEntidade(id)); }
    @Transactional(readOnly=true) public List<ContaResponse> listar() { return contas.findAll().stream().map(ContaResponse::de).toList(); }
    @Transactional public ContaResponse atualizar(Long id, ContaRequest r) {
        Conta c=obterEntidade(id);
        c.atualizar(r.nome().trim(), r.saldoInicial(), usuarios.obterEntidade(r.usuarioId()));
        return ContaResponse.de(c);
    }
    @Transactional public void excluir(Long id) {
        obterEntidade(id);
        if (lancamentos.existsByContaId(id)) throw new RegraNegocioException("Exclua os lançamentos da conta primeiro.");
        contas.deleteById(id);
    }
}
