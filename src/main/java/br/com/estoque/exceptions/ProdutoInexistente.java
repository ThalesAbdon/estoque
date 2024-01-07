package br.com.estoque.exceptions;

public class ProdutoInexistente extends RuntimeException{
    public ProdutoInexistente(Integer codigo) {
        super("Nao existe um produto cadastrado com o codigo " + codigo+ ".");
    }
}
