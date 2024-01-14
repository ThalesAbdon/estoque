package br.com.estoque.service;

import br.com.estoque.controller.request.ComprarLoteRequest;
import br.com.estoque.exceptions.EstoqueNaoEncontradoException;
import br.com.estoque.exceptions.ProdutoInexistente;
import br.com.estoque.model.Estoque;
import br.com.estoque.repository.EstoqueRepository;
import br.com.estoque.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComprarLoteService {
    @Autowired
    private EstoqueRepository estoqueRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    public void comprar(ComprarLoteRequest request) {
        int quantidadeTotalDisponivel = estoqueRepository.countQuantidadeTotal(request.getCodigoProduto());
        if (quantidadeTotalDisponivel < request.getQuantidade()) {
            throw new RuntimeException("Não há estoque suficiente para atender à quantidade desejada.");
        }

        var produto = produtoRepository.findByCodigo(request.getCodigoProduto());
        if ( produto == null) {
            throw new ProdutoInexistente(request.getCodigoProduto());
        }

        List<Estoque> produtosProximosDeVencer = estoqueRepository.findProdutosMaisProximosDeVencer(request.getCodigoProduto());

        if (produtosProximosDeVencer.size() == 0) {
            throw new EstoqueNaoEncontradoException(request.getCodigoProduto());
        }

        for (Estoque estoque : produtosProximosDeVencer) {
            int quantidadeDisponivel = estoque.getQuantidade();

            if (quantidadeDisponivel > 0) {
                Integer quantidadeAVender = Math.min(quantidadeDisponivel, request.getQuantidade());
                estoque.setQuantidade(quantidadeDisponivel - quantidadeAVender);
                estoqueRepository.save(estoque);
                request.setQuantidade(request.getQuantidade() - quantidadeAVender);

                System.out.println("Vendido " + quantidadeAVender + " produtos com vencimento em " + estoque.getDataValidade());

                if (request.getQuantidade() == 0) {
                    // Todas as unidades desejadas foram vendidas
                    break;
                }
            }
        }
    }
}

