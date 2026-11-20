package br.edu.faculdade.financeiro.service;

import br.edu.faculdade.financeiro.dto.UsuarioRequest;
import br.edu.faculdade.financeiro.model.Usuario;
import br.edu.faculdade.financeiro.repository.ContaRepository;
import br.edu.faculdade.financeiro.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {
    @Mock UsuarioRepository usuarios;
    @Mock ContaRepository contas;
    @Mock PasswordEncoder passwordEncoder;
    @InjectMocks UsuarioService service;

    @Test
    void cadastroArmazenaSenhaCodificadaENaoRetornaHash() {
        when(usuarios.existsByEmailIgnoreCase("ana@example.com")).thenReturn(false);
        when(passwordEncoder.encode("senha123")).thenReturn("hash-seguro");
        when(usuarios.save(any(Usuario.class))).thenAnswer(invocacao -> invocacao.getArgument(0));

        var resposta = service.criar(new UsuarioRequest(" Ana ", "ANA@example.com", "senha123"));

        assertEquals("Ana", resposta.nome());
        assertEquals("ana@example.com", resposta.email());
        verify(passwordEncoder).encode("senha123");
        verify(usuarios).save(argThat(usuario -> usuario.getSenhaHash().equals("hash-seguro")));
    }
}
