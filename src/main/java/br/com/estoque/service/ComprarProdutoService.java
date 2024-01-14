package br.com.estoque.service;

import br.com.estoque.controller.request.ComprarProdutoRequest;
import br.com.estoque.exceptions.*;
import br.com.estoque.repository.EstoqueRepository;
import br.com.estoque.repository.FornecedorRepository;
import br.com.estoque.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComprarProdutoService {
    @Autowired
    private EstoqueRepository estoqueRepository;

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private FornecedorRepository fornecedorRepository;
    public void comprar(ComprarProdutoRequest request) {
        var produto = produtoRepository.findByCodigo(request.getCodigoProduto());
        if ( produto == null) {
            throw new ProdutoInexistente(request.getCodigoProduto());
        }

        var fornecedor = fornecedorRepository.findByCnpj(request.getCnpjFornecedor());
        if ( fornecedor == null) {
            throw new FornecedorInexistente(request.getCnpjFornecedor());
        }

        var estoque = estoqueRepository.findByProdutoAndFornecedor(produto, fornecedor);
        if (estoque == null) {
            throw new EstoqueNaoEncontradoException(request.getCodigoProduto());
        }

        if (estoque.getQuantidade() < request.getQuantidade()) {
            throw new QuantidadeInsuficienteException();
        }

        estoque.setQuantidade(estoque.getQuantidade() - request.getQuantidade());
        estoqueRepository.save(estoque);
    }
}
