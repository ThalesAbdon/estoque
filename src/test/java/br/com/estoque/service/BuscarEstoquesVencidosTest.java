package br.com.estoque.service;

import br.com.estoque.controller.response.FornecedorResponse;
import br.com.estoque.controller.response.ProdutoResponse;
import br.com.estoque.model.Fornecedor;
import br.com.estoque.model.Produto;
import br.com.estoque.repository.EstoqueRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BuscarEstoquesVencidosTest {
    @InjectMocks
    private BuscarEstoquesVencidos estoqueService;

    @Mock
    private EstoqueRepository estoqueRepository;

    @Test
    public void testGetFornecedoresComEstoqueVencido() {
        List<Object[]> resultadoEsperado = new ArrayList<>();

        Fornecedor fornecedorExemplo = new Fornecedor();
        fornecedorExemplo.setCnpj("123456789");
        fornecedorExemplo.setNome("Fornecedor Exemplo");

        Produto produtoExemplo = new Produto();
        produtoExemplo.setCodigo(1);
        produtoExemplo.setEstoqueMinimo(10);

        Object[] exemplo1 = { fornecedorExemplo, produtoExemplo, 5 };
        resultadoEsperado.add(exemplo1);

        when(estoqueRepository.findFornecedoresComEstoqueVencido()).thenReturn(resultadoEsperado);

        List<Map<String, Object>> fornecedoresComEstoqueVencido = estoqueService.getFornecedoresComEstoqueVencido();

        assertNotNull(fornecedoresComEstoqueVencido);
        assertFalse(fornecedoresComEstoqueVencido.isEmpty());

        Map<String, Object> primeiroItem = fornecedoresComEstoqueVencido.get(0);
        assertNotNull(primeiroItem);

        FornecedorResponse fornecedorResponse = (FornecedorResponse) primeiroItem.get("fornecedor");
        assertNotNull(fornecedorResponse);
        assertEquals("123456789", fornecedorResponse.getCnpj());
        assertEquals("Fornecedor Exemplo", fornecedorResponse.getNome());

        ProdutoResponse produtoResponse = (ProdutoResponse) primeiroItem.get("produto");
        assertNotNull(produtoResponse);
        assertEquals(1, produtoResponse.getCodigo());
        assertEquals(10, produtoResponse.getEstoqueMinimo());
        assertEquals(5, produtoResponse.getQuantidade());
    }
}