package br.edu.faculdade.financeiro.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;

@Service
public class JwtService {
    private final SecretKey chave;
    private final long validadeMs;

    public JwtService(
        @Value("${app.jwt.secret}") String segredo,
        @Value("${app.jwt.expiration-ms}") long validadeMs
    ) {
        this.chave = Keys.hmacShaKeyFor(segredo.getBytes(StandardCharsets.UTF_8));
        this.validadeMs = validadeMs;
    }

    public String gerarToken(String email) {
        Instant agora = Instant.now();
        return Jwts.builder()
            .subject(email)
            .issuedAt(Date.from(agora))
            .expiration(Date.from(agora.plusMillis(validadeMs)))
            .signWith(chave)
            .compact();
    }

    public String extrairEmail(String token) {
        return Jwts.parser()
            .verifyWith(chave)
            .build()
            .parseSignedClaims(token)
            .getPayload()
            .getSubject();
    }
}
