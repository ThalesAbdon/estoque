package br.com.estoque.controller;

import br.com.estoque.controller.request.ComprarProdutoRequest;
import br.com.estoque.controller.request.IncluirProdutoRequest;
import br.com.estoque.controller.response.ProdutoResponse;
import br.com.estoque.service.BuscarProduto;
import br.com.estoque.service.ComprarProdutoService;
import br.com.estoque.service.IncluirProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private BuscarProduto buscarProduto;
    @Autowired
    private ComprarProdutoService comprarProdutoService;
    @Autowired
    private IncluirProdutoService incluirProdutoService;

    @DeleteMapping("{id}/desfazer")
    public String desfazer(@PathVariable Long id) {
        return "teste";
    }

    @GetMapping("{codigo}")
    public ProdutoResponse pesquisar(@PathVariable Integer codigo) {
        return buscarProduto.pesquisar(codigo);
    }

    @PostMapping("/comprar")
    public void comprar(@RequestBody ComprarProdutoRequest request) {
        comprarProdutoService.comprar(request);
    }

    @PostMapping("/incluir")
    public void incluir(@RequestBody IncluirProdutoRequest request) {
        incluirProdutoService.incluirProduto(request);
    }
}