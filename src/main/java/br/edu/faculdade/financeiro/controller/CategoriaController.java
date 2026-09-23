package br.edu.faculdade.financeiro.controller;
import br.edu.faculdade.financeiro.dto.*;
import br.edu.faculdade.financeiro.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import java.net.URI;
import java.util.List;

@RestController @RequestMapping("/api/categorias")
public class CategoriaController {
    private final CategoriaService service;
    public CategoriaController(CategoriaService service) { this.service=service; }
    @PostMapping public ResponseEntity<CategoriaResponse> criar(@Valid @RequestBody CategoriaRequest r) {
        var criado=service.criar(r); return ResponseEntity.created(URI.create("/api/categorias/"+criado.id())).body(criado);
    }
    @GetMapping public List<CategoriaResponse> listar() { return service.listar(); }
    @GetMapping("/{id}") public CategoriaResponse obter(@PathVariable Long id) { return service.obter(id); }
    @PutMapping("/{id}") public CategoriaResponse atualizar(@PathVariable Long id,@Valid @RequestBody CategoriaRequest r) { return service.atualizar(id,r); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void excluir(@PathVariable Long id) { service.excluir(id); }
}
