package br.edu.faculdade.financeiro.error;
public class RecursoNaoEncontradoException extends RuntimeException {
    public RecursoNaoEncontradoException(String tipo, Long id) { super(tipo + " com ID " + id + " não encontrado(a)."); }
}
