package br.com.estoque.service;

import br.com.estoque.controller.request.IncluirProdutoRequest;
import br.com.estoque.exceptions.DadosInvalidosException;
import br.com.estoque.exceptions.ProdutoJaExisteException;
import br.com.estoque.mapper.ProdutoMapper;
import br.com.estoque.model.Produto;
import br.com.estoque.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IncluirProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public void incluirProduto(IncluirProdutoRequest request) {
        // Verificar se já existe um produto com o mesmo código
        if (produtoRepository.existsByCodigo(request.getCodigo())) {
            throw new ProdutoJaExisteException(request.getCodigo());
        }

        // Validar os dados de entrada
        if (request.getCodigo() <= 0 || request.getEstoqueMinimo() < 0 || request.getLucro() < 0 ||
                request.getPrecoDeCompra() < 0 || request.getQuantidade() < 0 || request.getPrecoDeVenda() < 0) {
            throw new DadosInvalidosException();
        }

        // Mapear os dados do request para um objeto Produto usando o ProdutoMapper
        Produto produto = ProdutoMapper.toProduto(request);

        // Salvar o produto no banco de dados
        produtoRepository.save(produto);
    }
}