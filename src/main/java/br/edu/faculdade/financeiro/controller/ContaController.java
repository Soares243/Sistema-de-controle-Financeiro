package br.edu.faculdade.financeiro.controller;
import br.edu.faculdade.financeiro.dto.*;
import br.edu.faculdade.financeiro.service.ContaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import java.net.URI;
import java.util.List;

@RestController @RequestMapping("/api/contas")
public class ContaController {
    private final ContaService service;
    public ContaController(ContaService service) { this.service=service; }
    @PostMapping public ResponseEntity<ContaResponse> criar(@Valid @RequestBody ContaRequest r) {
        var criado=service.criar(r); return ResponseEntity.created(URI.create("/api/contas/"+criado.id())).body(criado);
    }
    @GetMapping public List<ContaResponse> listar() { return service.listar(); }
    @GetMapping("/{id}") public ContaResponse obter(@PathVariable Long id) { return service.obter(id); }
    @PutMapping("/{id}") public ContaResponse atualizar(@PathVariable Long id,@Valid @RequestBody ContaRequest r) { return service.atualizar(id,r); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void excluir(@PathVariable Long id) { service.excluir(id); }
}
