package br.com.estoque.controller.response;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FornecedorResponse {
    private String cnpj;
    private String nome;
}
