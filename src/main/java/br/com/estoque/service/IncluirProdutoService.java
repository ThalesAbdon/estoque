package br.com.estoque.service;

import br.com.estoque.controller.request.IncluirProdutoRequest;
import br.com.estoque.controller.response.ProdutoResponse;
import br.com.estoque.exceptions.DadosInvalidosException;
import br.com.estoque.exceptions.ProdutoJaExisteException;
import br.com.estoque.model.Produto;
import br.com.estoque.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static br.com.estoque.mapper.ProdutoMapper.mapToProduto;
import static br.com.estoque.mapper.ProdutoMapper.mapToResponse;

@Service
public class IncluirProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;


    public ProdutoResponse save(IncluirProdutoRequest request) {
        if (produtoRepository.findByCodigo(request.getCodigo()) != null) {
            throw new ProdutoJaExisteException(request.getCodigo());
        }

        if (request.getCodigo() < 0 ||
                request.getDescricao() == null ||
                request.getDescricao().isEmpty() ||
                request.getEstoqueMinimo() < 0 ||
                request.getLucro() < 0 ||
                request.getPrecoDeCompra() < 0 ||
                request.getQuantidade() < 0 ||
                request.getPrecoDeVenda() < 0) {
            throw new DadosInvalidosException();
        }
        Produto produto = mapToProduto(request);

        //produto.setEstoque(estoque);
        return mapToResponse(produtoRepository.save(produto));
    }
}
