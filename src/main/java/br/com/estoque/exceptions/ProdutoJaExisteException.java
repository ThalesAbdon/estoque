package br.com.estoque.exceptions;

public class ProdutoJaExisteException extends RuntimeException {
    public ProdutoJaExisteException(Integer codigo) {
        super("Produto com código " + codigo + " já existe.");
    }
}
