package br.com.estoque.exceptions;

public class DadosInvalidosException extends RuntimeException{
    public DadosInvalidosException() {
        super("Dados inválidos. Certifique-se de que todos os campos estão preenchidos corretamente.");
    }
}
