package br.com.estoque.service;


import br.com.estoque.controller.request.IncluirEstoqueRequest;
import br.com.estoque.exceptions.ProdutoJaExisteException;
import br.com.estoque.model.Estoque;
import br.com.estoque.repository.EstoqueRepository;
import br.com.estoque.repository.FornecedorRepository;
import br.com.estoque.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IncluirEstoqueService {
    @Autowired
    private EstoqueRepository estoqueRepository;

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private FornecedorRepository fornecedorRepository;

    public void save(IncluirEstoqueRequest request) {
        var produto = produtoRepository.findByCodigo(request.getCodigoProduto());

        if ( produto != null) {
            throw new ProdutoJaExisteException(request.getCodigoProduto());
        }
        var fornecedor = fornecedorRepository.findByCnpj(request.getCnpjFornecedor());
        if ( fornecedor != null) {
            throw new ProdutoJaExisteException(request.getCodigoProduto());
        }
        Estoque estoque = new Estoque();
        estoque.setProduto(produto);
        estoque.setFornecedor(fornecedor);

        estoqueRepository.save(estoque);
    }
}
