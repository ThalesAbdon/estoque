package br.com.estoque.service;

import br.com.estoque.controller.response.FornecedorResponse;
import br.com.estoque.controller.response.ProdutoResponse;
import br.com.estoque.model.Fornecedor;
import br.com.estoque.model.Produto;
import br.com.estoque.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BuscarEstoquesVencidos {
    @Autowired
    private EstoqueRepository estoqueRepository;

    public List<Map<String, Object>> getFornecedoresComEstoqueVencido() {
        var result = estoqueRepository.findFornecedoresComEstoqueVencido();

        if(result.isEmpty()) {
            return List.of();
        }

        return result.stream()
                .map(objects -> {
                    Fornecedor fornecedor = (Fornecedor) objects[0];
                    Produto produto = (Produto) objects[1];
                    var quantidade = (Integer) objects[2];

                    Map<String, Object> map = new HashMap<>();
                    map.put("fornecedor", FornecedorResponse.builder()
                            .cnpj(fornecedor.getCnpj())
                            .nome(fornecedor.getNome())
                            .build());
                    map.put("produto", ProdutoResponse.builder()
                            .codigo(produto.getCodigo())
                            .estoqueMinimo(produto.getEstoqueMinimo())
                            .quantidade(quantidade)
                            .build());
                    return map;
                })
                .collect(Collectors.toList());
    }
}
