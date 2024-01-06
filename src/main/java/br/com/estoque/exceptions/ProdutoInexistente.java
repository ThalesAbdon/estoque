package br.com.estoque.exceptions;

public class ProdutoInexistente extends RuntimeException{
    public ProdutoInexistente(Long codigo) {
        super("Nao existe um produto cadastrado com o codigo " + codigo+ ".");
    }
}
