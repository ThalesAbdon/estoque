package br.com.estoque.testeIntegracao;


import br.com.estoque.controller.response.FornecedorResponse;
import br.com.estoque.controller.response.ProdutoResponse;
import br.com.estoque.model.Estoque;
import br.com.estoque.model.Fornecedor;
import br.com.estoque.model.Produto;
import br.com.estoque.repository.EstoqueRepository;
import br.com.estoque.repository.FornecedorRepository;
import br.com.estoque.repository.ProdutoRepository;
import br.com.estoque.service.BuscarEstoquesVencidos;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BuscarEstoquesVencidosIntegracao {
    @Autowired
    private EstoqueRepository estoqueRepository;
    @Autowired
    private BuscarEstoquesVencidos buscarEstoquesVencidos;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @BeforeAll
    public void iniciar() {
        estoqueRepository.deleteAll();
        produtoRepository.deleteAll();
        fornecedorRepository.deleteAll();

        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setCnpj("123456789");
        fornecedor.setNome("Fornecedor A");
        fornecedorRepository.save(fornecedor);

        Produto produto = new Produto();
        produto.setCodigo(1);
        produto.setQuantidade(100);
        produto.setEstoqueMinimo(10);
        produto.setDescricao("Produto A");
        produto.setPrecoDeCompra(50.0);
        produto.setPrecoDeVenda(100.0);
        produto.setLucro(50.0);
        produto.setFornecedor(fornecedor);
        produtoRepository.save(produto);

        List<Estoque> estoques = new ArrayList<>();

        Estoque estoque1 = new Estoque();
        estoque1.setFornecedor(fornecedor);
        estoque1.setProduto(produto);
        estoque1.setDataValidade(LocalDate.now().plusDays(10)); // Data futura
        estoque1.setQuantidade(20);
        estoques.add(estoque1);

        Estoque estoque2 = new Estoque();
        estoque2.setFornecedor(fornecedor);
        estoque2.setProduto(produto);
        estoque2.setDataValidade(LocalDate.now().minusDays(5)); // Data vencida
        estoque2.setQuantidade(15);
        estoques.add(estoque2);

        estoqueRepository.saveAll(estoques);
    }

    @Test
    public void testGetFornecedoresComEstoqueVencido() {
        List<Map<String, Object>> fornecedoresComEstoqueVencido = buscarEstoquesVencidos.getFornecedoresComEstoqueVencido();

        assertThat(fornecedoresComEstoqueVencido).isNotNull();
        assertThat(fornecedoresComEstoqueVencido).hasSize(1);

        Map<String, Object> primeiroItem = fornecedoresComEstoqueVencido.get(0);
        assertThat(primeiroItem).containsKeys("fornecedor", "produto");

        var fornecedorResponse = (FornecedorResponse) primeiroItem.get("fornecedor");
        var produtoResponse = (ProdutoResponse) primeiroItem.get("produto");

        assertThat(fornecedorResponse.getCnpj()).isEqualTo("123456789");
        assertThat(fornecedorResponse.getNome()).isEqualTo("Fornecedor A");
        assertThat(produtoResponse.getCodigo()).isEqualTo(1);
        assertThat(produtoResponse.getEstoqueMinimo()).isEqualTo(10);
        assertThat(produtoResponse.getQuantidade()).isEqualTo(15);
    }

    @Test
    public void deveRetornaListaVazia() {
        estoqueRepository.deleteAll();
        List<Map<String, Object>> fornecedoresComEstoqueVencido = buscarEstoquesVencidos.getFornecedoresComEstoqueVencido();

        assertThat(fornecedoresComEstoqueVencido).hasSize(0);
    }
}
