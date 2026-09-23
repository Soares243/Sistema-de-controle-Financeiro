package br.edu.faculdade.financeiro.service;

import br.edu.faculdade.financeiro.dto.LancamentoRequest;
import br.edu.faculdade.financeiro.error.RegraNegocioException;
import br.edu.faculdade.financeiro.model.*;
import br.edu.faculdade.financeiro.repository.LancamentoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LancamentoServiceTest {
    @Mock LancamentoRepository lancamentos;
    @Mock ContaService contas;
    @Mock CategoriaService categorias;
    @InjectMocks LancamentoService service;

    @Test void rejeitaTipoDiferenteDaCategoria() {
        var pedido=new LancamentoRequest("Mercado",new BigDecimal("50.00"),LocalDate.of(2026,9,23),TipoLancamento.DESPESA,1L,2L);
        when(categorias.obterEntidade(2L)).thenReturn(new Categoria("Salário",TipoLancamento.RECEITA));
        assertThrows(RegraNegocioException.class, () -> service.criar(pedido));
        verifyNoInteractions(contas, lancamentos);
    }
}
