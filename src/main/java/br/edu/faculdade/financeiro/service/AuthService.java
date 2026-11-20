package br.edu.faculdade.financeiro.service;

import br.edu.faculdade.financeiro.dto.LoginRequest;
import br.edu.faculdade.financeiro.dto.TokenResponse;
import br.edu.faculdade.financeiro.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public TokenResponse login(LoginRequest request) {
        var autenticacao = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.email(), request.senha())
        );
        return new TokenResponse(jwtService.gerarToken(autenticacao.getName()), "Bearer");
    }
}
