package br.edu.faculdade.financeiro.security;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JwtServiceTest {
    @Test
    void tokenGeradoPodeSerLidoComMesmoEmail() {
        var service = new JwtService("chave-de-teste-com-tamanho-suficiente-123456", 60_000);

        String token = service.gerarToken("ana@example.com");

        assertEquals("ana@example.com", service.extrairEmail(token));
    }
}
