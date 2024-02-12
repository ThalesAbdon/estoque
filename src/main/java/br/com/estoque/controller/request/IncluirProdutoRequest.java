package br.com.estoque.controller.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IncluirProdutoRequest {

    private Integer codigo;
    private String descricao;
    private Integer estoqueMinimo;
    private Double lucro;
    private Double precoDeCompra;
    private Integer quantidade;
    private Double precoDeVenda;
}