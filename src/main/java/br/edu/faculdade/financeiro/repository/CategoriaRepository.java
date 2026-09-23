package br.edu.faculdade.financeiro.repository;
import br.edu.faculdade.financeiro.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CategoriaRepository extends JpaRepository<Categoria,Long> {}
