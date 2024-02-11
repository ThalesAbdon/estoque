//package br.com.estoque.testeIntegracao;
//
//import br.com.estoque.controller.response.ProdutoResponse;
//import br.com.estoque.exceptions.ProdutoJaExisteException;
//import br.com.estoque.repository.ProdutoRepository;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class IncluirProdutoTest {
//    @Autowired
//    private IncluirProdutoService incluirProdutoService;
//
//    @Autowired
//    private ProdutoRepository produtoRepository;
//
//    @BeforeAll
//    public void iniciar() {
//        produtoRepository.deleteAll();
//    }
//
//    @Test
//    public void quandoIncluirProdutoValido_entaoDeveSalvar() {
//        // Dados de entrada
//        IncluirProdutoRequest request = new IncluirProdutoRequest();
//        request.setCodigo(1);
//        request.setDescricao("Produto Teste");
//        request.setEstoqueMinimo(10);
//        request.setLucro(20.0);
//        request.setPrecoDeCompra(100.0);
//        request.setQuantidade(50);
//        request.setPrecoDeVenda(120.0);
//
//        // Executar o serviço
//        ProdutoResponse response = incluirProdutoService.save(request);
//
//        // Verificar o resultado
//        assertNotNull(response);
//        assertNotNull(response.getId()); // Supondo que ProdutoResponse tenha um campo 'id'
//        assertEquals("Produto Teste", response.getDescricao());
//
//        // Limpeza (opcional, dependendo de como o banco de dados em memória é configurado)
//        produtoRepository.deleteById(response.getId());
//    }
//
//    @Test
//    public void quandoIncluirProdutoComCodigoExistente_entaoDeveLancarExcecao() {
//        // Dados de entrada
//        IncluirProdutoRequest request1 = new IncluirProdutoRequest();
//        request1.setCodigo(2);
//        request1.setDescricao("Produto Existente");
//        request1.setEstoqueMinimo(5);
//        request1.setLucro(15.0);
//        request1.setPrecoDeCompra(50.0);
//        request1.setQuantidade(20);
//        request1.setPrecoDeVenda(65.0);
//
//        IncluirProdutoRequest request2 = new IncluirProdutoRequest();
//        request2.setCodigo(2); // Mesmo código do request1
//        request2.setDescricao("Produto Duplicado");
//        request2.setEstoqueMinimo(5);
//        request2.setLucro(15.0);
//        request2.setPrecoDeCompra(60.0);
//        request2.setQuantidade(25);
//        request2.setPrecoDeVenda(75.0);
//
//        // Executar o primeiro para inserir
//        ProdutoResponse response = incluirProdutoService.save(request1);
//
//        // Tentativa de inserir o segundo com código duplicado
//        assertThrows(ProdutoJaExisteException.class, () -> {
//            incluirProdutoService.save(request2);
//        });
//
//        // Limpeza
//        produtoRepository.deleteById(response.getId());
//    }
//}
