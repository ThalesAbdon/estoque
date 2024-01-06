package br.com.estoque.service;

import br.com.estoque.controller.response.ProdutoResponse;
import br.com.estoque.exceptions.ProdutoInexistente;
import br.com.estoque.mapper.ProdutoMapper;
import br.com.estoque.model.Produto;
import br.com.estoque.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuscarProduto {

    @Autowired
    private ProdutoRepository produtoRepository;

    public ProdutoResponse Pesquisar(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoInexistente(id));

        return ProdutoMapper.mapToResponse(produto);
    }
}
