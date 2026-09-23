package br.edu.faculdade.financeiro.repository;
import br.edu.faculdade.financeiro.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.*;
public interface LancamentoRepository extends JpaRepository<Lancamento,Long> {
    Page<Lancamento> findByContaId(Long contaId, Pageable pageable);
    boolean existsByContaId(Long contaId);
    boolean existsByCategoriaId(Long categoriaId);
}
