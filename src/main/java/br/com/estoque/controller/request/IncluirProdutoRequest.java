package br.com.estoque.controller.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IncluirProdutoRequest {

    private Integer codigo;

    private Integer quantidade;

    private Integer estoqueMinimo;

    private String descricao;

    private Double precoDeCompra;

    private Double precoDeVenda;

    private Double lucro;

    private Long fornecedorId;

    private Long estoqueId;
}
