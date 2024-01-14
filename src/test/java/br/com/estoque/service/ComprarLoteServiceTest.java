package br.com.estoque.service;

import br.com.estoque.controller.request.ComprarLoteRequest;
import br.com.estoque.exceptions.EstoqueNaoEncontradoException;
import br.com.estoque.exceptions.ProdutoInexistente;
import br.com.estoque.model.Estoque;
import br.com.estoque.model.Produto;
import br.com.estoque.repository.EstoqueRepository;
import br.com.estoque.repository.FornecedorRepository;
import br.com.estoque.repository.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ComprarLoteServiceTest {


    @InjectMocks
    private ComprarLoteService tested;
    @Mock
    private EstoqueRepository estoqueRepository;
    @Mock
    private ProdutoRepository produtoRepository;


    @BeforeEach
    public void setup() {
        // Inicialize os mocks antes de cada teste
        // MockitoAnnotations.openMocks(this);
    }
    @Test
    @DisplayName("Deve comprar com sucesso")
    void comprar() {

        ComprarLoteRequest request = new ComprarLoteRequest();
        request.setCodigoProduto(1);
        request.setQuantidade(2);

        Produto produto = new Produto();
        produto.setCodigo(1);

        Estoque estoque = new Estoque(); // Configurar o objeto Estoque conforme necessário
        estoque.setProduto(produto);
        estoque.setQuantidade(10);

        List<Estoque> estoquesProximosDeVencer = Collections.singletonList(estoque);

        when(produtoRepository.findByCodigo(1)).thenReturn(produto);

        when(estoqueRepository.countQuantidadeTotal(1)).thenReturn(10);
        when(estoqueRepository.findProdutosMaisProximosDeVencer(1)).thenReturn(estoquesProximosDeVencer);

        tested.comprar(request);

        verify(estoqueRepository, times(1)).save(any(Estoque.class));

        assertEquals(8, estoque.getQuantidade());
    }

    @Test
    public void testComprarQuantidadeExcedente() {
        ComprarLoteRequest request = new ComprarLoteRequest(); // Configurar o objeto conforme necessário
        request.setQuantidade(10); // Quantidade desejada maior do que o estoque disponível
        request.setCodigoProduto(1);

        // Configurar o cenário onde não há estoque suficiente
        when(estoqueRepository.countQuantidadeTotal(any())).thenReturn(5); // Quantidade total disponível

        // Verificar se a exceção é lançada
        assertThrows(RuntimeException.class, () -> tested.comprar(request));
    }

    @Test
    public void testProdutoInexistente() {
        ComprarLoteRequest request = new ComprarLoteRequest(); // Configurar o objeto conforme necessário
        request.setQuantidade(1);
        request.setCodigoProduto(123231);

        // Configurar o cenário onde o produto não existe
        when(estoqueRepository.countQuantidadeTotal(any())).thenReturn(5);
        when(produtoRepository.findByCodigo(any())).thenReturn(null);

        // Verificar se a exceção é lançada
        assertThrows(ProdutoInexistente.class, () -> tested.comprar(request));
    }

    @Test
    public void testEstoqueNaoEncontrado() {
        ComprarLoteRequest request = new ComprarLoteRequest(); // Configurar o objeto conforme necessário
        request.setQuantidade(1);
        request.setCodigoProduto(123231);

        // Configurar o cenário onde o produto não existe
        when(estoqueRepository.countQuantidadeTotal(any())).thenReturn(5);
        when(produtoRepository.findByCodigo(any())).thenReturn(new Produto());
        when(estoqueRepository.findProdutosMaisProximosDeVencer(any())).thenReturn(Collections.emptyList());

        // Verificar se a exceção é lançada
        assertThrows(EstoqueNaoEncontradoException.class, () -> tested.comprar(request));
    }
}