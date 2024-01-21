package br.com.estoque.controller.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComprarLoteRequest {

    private Integer quantidade;

    private Integer codigoProduto;
}
