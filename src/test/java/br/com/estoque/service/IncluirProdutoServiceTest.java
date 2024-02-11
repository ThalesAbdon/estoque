//package br.com.estoque.service;
//
//import br.com.estoque.controller.response.ProdutoResponse;
//import br.com.estoque.exceptions.DadosInvalidosException;
//import br.com.estoque.exceptions.ProdutoJaExisteException;
//import br.com.estoque.model.Produto;
//import br.com.estoque.repository.ProdutoRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//class IncluirProdutoServiceTest {
//
//    @InjectMocks
//    private IncluirProdutoService tested;
//
//    @Mock
//    private ProdutoRepository produtoRepository;
//
//    @Test
//    public void testSalvarNovoProduto() {
//        IncluirProdutoRequest request = new IncluirProdutoRequest();
//
//        request.setCodigo(1);
//        request.setDescricao("Teste");
//        request.setEstoqueMinimo(1);
//        request.setLucro(0.0);
//        request.setPrecoDeCompra(0.0);
//        request.setPrecoDeVenda(0.0);
//        request.setQuantidade(1);
//
//        when(produtoRepository.findByCodigo(request.getCodigo())).thenReturn(null);
//
//        Produto produtoSalvo = new Produto();
//
//        when(produtoRepository.save(any(Produto.class))).thenReturn(produtoSalvo);
//
//        ProdutoResponse response = tested.save(request);
//
//        assertNotNull(response);
//    }
//
//    @Test
//    public void testProdutoJaExiste() {
//        IncluirProdutoRequest request = new IncluirProdutoRequest();
//        request.setCodigo(1);
//
//        when(produtoRepository.findByCodigo(request.getCodigo())).thenReturn(new Produto());
//
//        assertThrows(ProdutoJaExisteException.class, () -> tested.save(request));
//    }
//
//    @Test
//    public void testDadosInvalidos() {
//        IncluirProdutoRequest request = new IncluirProdutoRequest();
//        request.setCodigo(1);
//        request.setDescricao("Teste");
//        request.setEstoqueMinimo(-1);
//        request.setLucro(0.0);
//        request.setPrecoDeCompra(0.0);
//        request.setPrecoDeVenda(0.0);
//        request.setQuantidade(1);
//
//        when(produtoRepository.findByCodigo(request.getCodigo())).thenReturn(null);
//
//        assertThrows(DadosInvalidosException.class, () -> tested.save(request));
//    }
//}