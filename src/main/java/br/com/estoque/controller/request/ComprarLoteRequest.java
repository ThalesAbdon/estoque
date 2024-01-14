package br.com.estoque.controller.request;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class ComprarLoteRequest {
    @NonNull
    @Min(1)
    private Integer quantidade;
    @NonNull
    private Integer codigoProduto;
}
