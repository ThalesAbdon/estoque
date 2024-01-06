package br.com.estoque.controller.request;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class IncluirEstoqueRequest {
    @NonNull
    private String cnpjFornecedor;
    @NonNull
    private Integer codigoProduto;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate validadeProdutos;
}
