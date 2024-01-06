package br.com.estoque.controller.response;

import br.com.estoque.model.Estoque;
import br.com.estoque.model.Fornecedor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoResponse {
    private Integer codigo;

    private Integer quantidade;

    private Integer estoqueMinimo;

    private String descricao;

    private Double precoDeCompra;

    private Double precoDeVenda;

    private Double lucro;
}
