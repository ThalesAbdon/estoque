package br.com.estoque.repository;

import br.com.estoque.model.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
    Fornecedor findByCnpj(String cnpj);
}
