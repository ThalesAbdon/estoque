package br.com.estoque.service;

import br.com.estoque.controller.response.FornecedorResponse;
import br.com.estoque.controller.response.ProdutoResponse;
import br.com.estoque.model.Estoque;
import br.com.estoque.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BuscarEstoquesVencidos {

    @Autowired
    private EstoqueRepository estoqueRepository;

    public List<Map<String, Object>> buscarEstoquesVencidos() {
        var estoquesVencidos = estoqueRepository.findAll().stream()
                .filter(estoque -> estoque.getDataValidade().isBefore(LocalDate.now()))
                .collect(Collectors.toList());

        return estoquesVencidos.stream()
                .map(this::mapToEstoqueVencidoResponse)
                .collect(Collectors.toList());
    }

    private Map<String, Object> mapToEstoqueVencidoResponse(Estoque estoque) {
        Map<String, Object> map = new HashMap<>();

        ProdutoResponse produto = new ProdutoResponse();
        produto.setCodigo(estoque.getProduto().getCodigo());
        produto.setEstoqueMinimo(estoque.getProduto().getEstoqueMinimo());
        produto.setQuantidade(estoque.getQuantidade());

        FornecedorResponse fornecedor = new FornecedorResponse();
        fornecedor.setCnpj(estoque.getFornecedor().getCnpj());
        fornecedor.setNome(estoque.getFornecedor().getNome());

        map.put("produto", produto);
        map.put("fornecedor", fornecedor);

        return map;
    }
}