package br.edu.faculdade.financeiro.service;
import br.edu.faculdade.financeiro.dto.*;
import br.edu.faculdade.financeiro.error.*;
import br.edu.faculdade.financeiro.model.Usuario;
import br.edu.faculdade.financeiro.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarios;
    private final ContaRepository contas;
    public UsuarioService(UsuarioRepository usuarios, ContaRepository contas) { this.usuarios=usuarios; this.contas=contas; }
    public Usuario obterEntidade(Long id) { return usuarios.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Usuário", id)); }
    @Transactional public UsuarioResponse criar(UsuarioRequest r) {
        if (usuarios.existsByEmailIgnoreCase(r.email())) throw new RegraNegocioException("E-mail já cadastrado.");
        return UsuarioResponse.de(usuarios.save(new Usuario(r.nome().trim(), r.email().trim().toLowerCase())));
    }
    @Transactional(readOnly=true) public UsuarioResponse obter(Long id) { return UsuarioResponse.de(obterEntidade(id)); }
    @Transactional(readOnly=true) public List<UsuarioResponse> listar() { return usuarios.findAll().stream().map(UsuarioResponse::de).toList(); }
    @Transactional public UsuarioResponse atualizar(Long id, UsuarioRequest r) {
        Usuario u=obterEntidade(id);
        if (usuarios.existsByEmailIgnoreCaseAndIdNot(r.email(), id)) throw new RegraNegocioException("E-mail já cadastrado.");
        u.atualizar(r.nome().trim(), r.email().trim().toLowerCase());
        return UsuarioResponse.de(u);
    }
    @Transactional public void excluir(Long id) {
        obterEntidade(id);
        if (contas.existsByUsuarioId(id)) throw new RegraNegocioException("Exclua as contas do usuário primeiro.");
        usuarios.deleteById(id);
    }
}
