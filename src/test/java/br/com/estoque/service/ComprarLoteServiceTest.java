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

    @Test
    @DisplayName("Deve comprar com sucesso")
    void comprar() {

        ComprarLoteRequest request = new ComprarLoteRequest();
        request.setCodigoProduto(1);
        request.setQuantidade(2);

        Produto produto = new Produto();
        produto.setCodigo(1);

        Estoque estoque = new Estoque();
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
        ComprarLoteRequest request = new ComprarLoteRequest();
        request.setQuantidade(10);
        request.setCodigoProduto(1);

        when(estoqueRepository.countQuantidadeTotal(any())).thenReturn(5);

        assertThrows(RuntimeException.class, () -> tested.comprar(request));
    }

    @Test
    public void testProdutoInexistente() {
        ComprarLoteRequest request = new ComprarLoteRequest();
        request.setQuantidade(1);
        request.setCodigoProduto(123231);

        when(estoqueRepository.countQuantidadeTotal(any())).thenReturn(5);
        when(produtoRepository.findByCodigo(any())).thenReturn(null);

        assertThrows(ProdutoInexistente.class, () -> tested.comprar(request));
    }

    @Test
    public void testEstoqueNaoEncontrado() {
        ComprarLoteRequest request = new ComprarLoteRequest();
        request.setQuantidade(1);
        request.setCodigoProduto(123231);

        when(estoqueRepository.countQuantidadeTotal(any())).thenReturn(5);
        when(produtoRepository.findByCodigo(any())).thenReturn(new Produto());
        when(estoqueRepository.findProdutosMaisProximosDeVencer(any())).thenReturn(Collections.emptyList());

        assertThrows(EstoqueNaoEncontradoException.class, () -> tested.comprar(request));
    }
}