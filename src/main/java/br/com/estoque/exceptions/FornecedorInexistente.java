package br.com.estoque.exceptions;

public class FornecedorInexistente extends RuntimeException{
    public FornecedorInexistente(String cpnj) {
        super("Nao existe um produto cadastrado com o codigo " + cpnj+ ".");
    }
}
