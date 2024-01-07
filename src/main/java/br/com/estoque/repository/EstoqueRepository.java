package br.com.estoque.repository;

import br.com.estoque.model.Estoque;
import br.com.estoque.model.Fornecedor;
import br.com.estoque.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
    Estoque findByProdutoAndFornecedor(Produto produto, Fornecedor fornecedor);
}
