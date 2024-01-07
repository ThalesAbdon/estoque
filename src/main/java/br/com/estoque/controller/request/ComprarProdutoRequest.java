package br.com.estoque.controller.request;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class ComprarProdutoRequest {
    @NonNull
    private String cnpjFornecedor;
    @NonNull
    private Integer codigoProduto;
    private Integer quantidade;
    private Double precoDeCompra;
}
