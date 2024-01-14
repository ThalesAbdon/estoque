package br.com.estoque.exceptions;

import lombok.NonNull;

public class QuantidadeInsuficienteException extends RuntimeException {
    public QuantidadeInsuficienteException() {
        super("Quantidade insuficiente no estoque.");
    }
}
