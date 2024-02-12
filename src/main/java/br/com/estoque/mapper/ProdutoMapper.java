package br.com.estoque.mapper;

import br.com.estoque.controller.response.ProdutoResponse;
import br.com.estoque.model.Produto;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ProdutoMapper {

    public static ProdutoResponse mapToResponse(Produto produto) {
        ProdutoResponse response = new ProdutoResponse();
        response.setId(produto.getId());
        response.setCodigo(produto.getCodigo());
        response.setQuantidade(produto.getQuantidade());
        response.setEstoqueMinimo(produto.getEstoqueMinimo());
        response.setDescricao(produto.getDescricao());
        response.setPrecoDeCompra(produto.getPrecoDeCompra());
        response.setPrecoDeVenda(produto.getPrecoDeVenda());
        response.setLucro(produto.getLucro());
        return response;
    }
}
