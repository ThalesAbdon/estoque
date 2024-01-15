package br.com.estoque.service;

import br.com.estoque.controller.request.ComprarProdutoRequest;
import br.com.estoque.exceptions.FornecedorInexistente;
import br.com.estoque.exceptions.ProdutoInexistente;
import br.com.estoque.exceptions.QuantidadeInsuficienteException;
import br.com.estoque.model.Estoque;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ComprarProdutoServiceTest {

    @InjectMocks
    private ComprarProdutoService comprarProdutoService;

    @Mock
    private EstoqueRepository estoqueRepository;

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private FornecedorRepository fornecedorRepository;

    @Test
    public void testComprarProdutoComSucesso() {
        ComprarProdutoRequest request = new ComprarProdutoRequest();
        request.setCodigoProduto(1);
        request.setCnpjFornecedor("123456789");
        request.setQuantidade(10);

        Produto produto = new Produto(); // Configurar o objeto Produto conforme necessário
        when(produtoRepository.findByCodigo(request.getCodigoProduto())).thenReturn(produto);

        Fornecedor fornecedor = new Fornecedor(); // Configurar o objeto Fornecedor conforme necessário
        when(fornecedorRepository.findByCnpj(request.getCnpjFornecedor())).thenReturn(fornecedor);

        Estoque estoque = new Estoque(); // Configurar o objeto Estoque conforme necessário
        estoque.setQuantidade(10); // Estoque suficiente para a compra
        when(estoqueRepository.findByProdutoAndFornecedor(produto, fornecedor)).thenReturn(estoque);

        // Executar o método comprar
        comprarProdutoService.comprar(request);

        assertEquals(0, estoque.getQuantidade());
    }

    @Test
    public void testProdutoInexistente() {
        ComprarProdutoRequest request = new ComprarProdutoRequest();

        when(produtoRepository.findByCodigo(request.getCodigoProduto())).thenReturn(null);

        assertThrows(ProdutoInexistente.class, () -> comprarProdutoService.comprar(request));
    }

    @Test
    public void testFornecedorInexistente() {
        ComprarProdutoRequest request = new ComprarProdutoRequest();

        when(produtoRepository.findByCodigo(request.getCodigoProduto())).thenReturn(new Produto());
        when(fornecedorRepository.findByCnpj(request.getCnpjFornecedor())).thenReturn(null);

        assertThrows(FornecedorInexistente.class, () -> comprarProdutoService.comprar(request));
    }

    @Test
    public void testEstoqueInsuficiente() {
        ComprarProdutoRequest request = new ComprarProdutoRequest();
        request.setQuantidade(10);

        Produto produto = new Produto();
        when(produtoRepository.findByCodigo(request.getCodigoProduto())).thenReturn(produto);

        Fornecedor fornecedor = new Fornecedor();
        when(fornecedorRepository.findByCnpj(request.getCnpjFornecedor())).thenReturn(fornecedor);

        Estoque estoque = new Estoque();
        estoque.setQuantidade(5);
        when(estoqueRepository.findByProdutoAndFornecedor(produto, fornecedor)).thenReturn(estoque);

        assertThrows(QuantidadeInsuficienteException.class, () -> comprarProdutoService.comprar(request));
    }
}