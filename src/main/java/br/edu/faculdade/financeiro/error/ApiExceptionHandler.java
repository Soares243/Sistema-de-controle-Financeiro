package br.edu.faculdade.financeiro.error;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.dao.DataIntegrityViolationException;
import java.time.Instant;
import java.util.*;

@RestControllerAdvice
public class ApiExceptionHandler {
    private record Erro(Instant instante, int status, String mensagem, Object detalhes) {}
    @ExceptionHandler(RecursoNaoEncontradoException.class)
    ResponseEntity<Erro> naoEncontrado(RecursoNaoEncontradoException e) { return resposta(HttpStatus.NOT_FOUND, e.getMessage(), null); }
    @ExceptionHandler(RegraNegocioException.class)
    ResponseEntity<Erro> regra(RegraNegocioException e) { return resposta(HttpStatus.CONFLICT, e.getMessage(), null); }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<Erro> validacao(MethodArgumentNotValidException e) {
        Map<String,String> campos = new LinkedHashMap<>();
        e.getBindingResult().getFieldErrors().forEach(x -> campos.put(x.getField(), x.getDefaultMessage()));
        return resposta(HttpStatus.BAD_REQUEST, "Dados inválidos.", campos);
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    ResponseEntity<Erro> corpo(HttpMessageNotReadableException e) { return resposta(HttpStatus.BAD_REQUEST, "JSON inválido ou valor de enumeração incorreto.", null); }
    @ExceptionHandler(DataIntegrityViolationException.class)
    ResponseEntity<Erro> integridade(DataIntegrityViolationException e) { return resposta(HttpStatus.CONFLICT, "Operação viola restrição do banco de dados.", null); }
    private ResponseEntity<Erro> resposta(HttpStatus status, String mensagem, Object detalhes) {
        return ResponseEntity.status(status).body(new Erro(Instant.now(), status.value(), mensagem, detalhes));
    }
}
