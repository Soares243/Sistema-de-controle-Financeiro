package br.edu.faculdade.financeiro.controller;
import br.edu.faculdade.financeiro.dto.*;
import br.edu.faculdade.financeiro.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import java.net.URI;
import java.util.List;

@RestController @RequestMapping("/api/usuarios")
public class UsuarioController {
    private final UsuarioService service;
    public UsuarioController(UsuarioService service) { this.service=service; }
    @PostMapping public ResponseEntity<UsuarioResponse> criar(@Valid @RequestBody UsuarioRequest r) {
        var criado=service.criar(r); return ResponseEntity.created(URI.create("/api/usuarios/"+criado.id())).body(criado);
    }
    @GetMapping public List<UsuarioResponse> listar() { return service.listar(); }
    @GetMapping("/{id}") public UsuarioResponse obter(@PathVariable Long id) { return service.obter(id); }
    @PutMapping("/{id}") public UsuarioResponse atualizar(@PathVariable Long id,@Valid @RequestBody UsuarioRequest r) { return service.atualizar(id,r); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void excluir(@PathVariable Long id) { service.excluir(id); }
}
