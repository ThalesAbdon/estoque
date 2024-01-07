package br.com.estoque.repository;

import br.com.estoque.model.Estoque;
import br.com.estoque.model.Fornecedor;
import br.com.estoque.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
    Estoque findByProdutoAndFornecedor(Produto produto, Fornecedor fornecedor);

    @Query("SELECT e.fornecedor, e.produto, e.quantidade FROM Estoque e WHERE e.produto.estoqueMinimo > e.quantidade")
    List<Object[]> findFornecedoresComEstoqueBaixo();

    @Query("SELECT e.fornecedor, e.produto, e.quantidade FROM Estoque e WHERE e.dataValidade < CURRENT_DATE AND e.quantidade > 0")
    List<Object[]> findFornecedoresComEstoqueVencido();
}
