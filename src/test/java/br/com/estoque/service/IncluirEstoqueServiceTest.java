package br.com.estoque.service;

import br.com.estoque.controller.request.IncluirEstoqueRequest;
import br.com.estoque.exceptions.FornecedorInexistente;
import br.com.estoque.exceptions.ProdutoInexistente;
import br.com.estoque.model.Fornecedor;
import br.com.estoque.model.Produto;
import br.com.estoque.repository.EstoqueRepository;
import br.com.estoque.repository.FornecedorRepository;
import br.com.estoque.repository.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IncluirEstoqueServiceTest {

    @InjectMocks
    private IncluirEstoqueService incluirEstoqueService;

    @Mock
    private EstoqueRepository estoqueRepository;

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private FornecedorRepository fornecedorRepository;

    @Test
    public void testIncluirEstoqueComSucesso() {
        IncluirEstoqueRequest request = new IncluirEstoqueRequest();
        request.setCodigoProduto(1);
        request.setCnpjFornecedor("123");
        request.setQuantidade(1);
        request.setValidadeProdutos(LocalDate.now());

        Produto produto = new Produto();
        when(produtoRepository.findByCodigo(request.getCodigoProduto())).thenReturn(produto);

        Fornecedor fornecedor = new Fornecedor();
        when(fornecedorRepository.findByCnpj(request.getCnpjFornecedor())).thenReturn(fornecedor);

        incluirEstoqueService.save(request);

        verify(estoqueRepository).save(any());
    }

    @Test
    public void testProdutoInexistente() {
        IncluirEstoqueRequest request = new IncluirEstoqueRequest();

        when(produtoRepository.findByCodigo(request.getCodigoProduto())).thenReturn(null);

        assertThrows(ProdutoInexistente.class, () -> incluirEstoqueService.save(request));
    }

    @Test
    public void testFornecedorInexistente() {
        IncluirEstoqueRequest request = new IncluirEstoqueRequest();

        when(produtoRepository.findByCodigo(request.getCodigoProduto())).thenReturn(new Produto());

        assertThrows(FornecedorInexistente.class, () -> incluirEstoqueService.save(request));
    }
}