package br.edu.faculdade.financeiro.service;

import br.edu.faculdade.financeiro.dto.LoginRequest;
import br.edu.faculdade.financeiro.security.JwtService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {
    @Mock AuthenticationManager authenticationManager;
    @Mock JwtService jwtService;
    @InjectMocks AuthService service;

    @Test
    void loginRetornaTokenBearer() {
        var request = new LoginRequest("ana@example.com", "senha123");
        Authentication autenticacao = new UsernamePasswordAuthenticationToken(
            "ana@example.com", null
        );
        when(authenticationManager.authenticate(any())).thenReturn(autenticacao);
        when(jwtService.gerarToken("ana@example.com")).thenReturn("jwt-de-teste");

        var resposta = service.login(request);

        assertEquals("jwt-de-teste", resposta.token());
        assertEquals("Bearer", resposta.tipo());
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
    }
}
