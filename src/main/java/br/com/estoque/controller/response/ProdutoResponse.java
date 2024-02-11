package br.com.estoque.controller.response;

import br.com.estoque.model.Estoque;
import br.com.estoque.model.Fornecedor;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoResponse {
    private Long id;

    private Integer codigo;

    private Integer quantidade;

    private Integer estoqueMinimo;

    private String descricao;

    private Double precoDeCompra;

    private Double precoDeVenda;

    private Double lucro;
}
