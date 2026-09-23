package br.edu.faculdade.financeiro.controller;
import br.edu.faculdade.financeiro.dto.*;
import br.edu.faculdade.financeiro.service.LancamentoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import org.springframework.data.domain.*;
import java.net.URI;

@RestController @RequestMapping("/api/lancamentos")
public class LancamentoController {
    private final LancamentoService service;
    public LancamentoController(LancamentoService service) { this.service=service; }
    @PostMapping public ResponseEntity<LancamentoResponse> criar(@Valid @RequestBody LancamentoRequest r) {
        var criado=service.criar(r); return ResponseEntity.created(URI.create("/api/lancamentos/"+criado.id())).body(criado);
    }
    @GetMapping public Page<LancamentoResponse> listar(@RequestParam(required=false) Long contaId,
        @RequestParam(defaultValue="0") int page, @RequestParam(defaultValue="20") int size,
        @RequestParam(defaultValue="data") String sort, @RequestParam(defaultValue="desc") String direction) {
        if (page<0 || size<1 || size>100) throw new org.springframework.web.server.ResponseStatusException(HttpStatus.BAD_REQUEST,"Paginação inválida.");
        if (!java.util.Set.of("id","data","valor","descricao").contains(sort))
            throw new org.springframework.web.server.ResponseStatusException(HttpStatus.BAD_REQUEST,"Ordenação inválida.");
        Sort.Direction sentido;
        try { sentido=Sort.Direction.fromString(direction); }
        catch (IllegalArgumentException e) { throw new org.springframework.web.server.ResponseStatusException(HttpStatus.BAD_REQUEST,"Direção inválida."); }
        return service.listar(contaId, PageRequest.of(page,size,Sort.by(sentido,sort)));
    }
    @GetMapping("/{id}") public LancamentoResponse obter(@PathVariable Long id) { return service.obter(id); }
    @PutMapping("/{id}") public LancamentoResponse atualizar(@PathVariable Long id,@Valid @RequestBody LancamentoRequest r) { return service.atualizar(id,r); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void excluir(@PathVariable Long id) { service.excluir(id); }
}
