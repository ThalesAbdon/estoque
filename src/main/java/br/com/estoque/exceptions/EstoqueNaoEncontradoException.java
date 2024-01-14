package br.com.estoque.exceptions;

import lombok.NonNull;

public class EstoqueNaoEncontradoException extends RuntimeException{
    public EstoqueNaoEncontradoException(@NonNull Integer codigoProduto) {
        super("Estoque para o produto " + codigoProduto + " não encontrado.");
    }
}
