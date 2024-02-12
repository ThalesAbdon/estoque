package br.com.estoque.controller.response;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EstoqueVencidoResponse {

    private ProdutoResponse produto;
    private FornecedorResponse fornecedor;

    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FornecedorResponse {
        private String cnpj;
        private String nome;
    }
}