package br.edu.faculdade.financeiro.repository;
import br.edu.faculdade.financeiro.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ContaRepository extends JpaRepository<Conta,Long> {
    boolean existsByUsuarioId(Long usuarioId);
}
