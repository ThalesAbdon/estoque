package br.com.estoque.testeIntegracao;

import br.com.estoque.controller.request.ComprarLoteRequest;
import br.com.estoque.exceptions.DadosInvalidosException;
import br.com.estoque.exceptions.EstoqueNaoEncontradoException;
import br.com.estoque.exceptions.ProdutoInexistente;
import br.com.estoque.model.Estoque;
import br.com.estoque.model.Fornecedor;
import br.com.estoque.model.Produto;
import br.com.estoque.repository.EstoqueRepository;
import br.com.estoque.repository.FornecedorRepository;
import br.com.estoque.repository.ProdutoRepository;
import br.com.estoque.service.ComprarLoteService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ComprarLoteServiceTest {
    @Autowired
    private ComprarLoteService comprarLoteService;

    @Autowired
    private EstoqueRepository estoqueRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    private Estoque estoqueValido;

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


        Estoque estoque1 = new Estoque();
        estoque1.setFornecedor(fornecedor);
        estoque1.setProduto(produto);
        estoque1.setDataValidade(LocalDate.now().plusDays(10)); // Data futura
        estoque1.setQuantidade(20);
        estoqueValido = estoque1;
        estoqueRepository.save(estoque1);
    }
    @Test
    public void deveComprarLoteQuandoDadosSaoValidos() {
        ComprarLoteRequest request = new ComprarLoteRequest();
        request.setQuantidade(5);
        request.setCodigoProduto(1);

        comprarLoteService.comprar(request);

        var produtos = estoqueRepository.findAll();
        var produto = produtos.get(0);

        assertThat(produto.getQuantidade()).isEqualTo(15);
        assertThat(produto.getDataValidade()).isEqualTo(estoqueValido.getDataValidade());
        assertThat(produto.getFornecedor()).isEqualTo(estoqueValido.getFornecedor());
        assertThat(produto.getProduto()).isEqualTo(estoqueValido.getProduto());
    }

    @Test
    public void deveLancarExcecaoQuandoDadpsinvalidos() {
        ComprarLoteRequest request = new ComprarLoteRequest();
        request.setQuantidade(0);
        request.setCodigoProduto(1);

        assertThrows(DadosInvalidosException.class, () -> {
            comprarLoteService.comprar(request);
        });
    }
}