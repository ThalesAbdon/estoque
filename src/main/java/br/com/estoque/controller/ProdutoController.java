package br.com.estoque.controller;

import br.com.estoque.controller.request.IncluirProdutoRequest;
import br.com.estoque.controller.response.ProdutoResponse;
import br.com.estoque.service.BuscarProduto;
import br.com.estoque.service.IncluirProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private IncluirProdutoService incluirProdutoService;

    @Autowired
    private BuscarProduto buscarProduto;

    @PostMapping("/incluir")
    public ResponseEntity<String> incluir(@RequestBody IncluirProdutoRequest request) {
        try {
            incluirProdutoService.save(request);
            return ResponseEntity.status(HttpStatus.CREATED).body("Produto adicionado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao adicionar o produto.");
        }
    }

    @DeleteMapping("{id}/desfazer")
    public String desfazer(@PathVariable Long id) {
        return "teste";
    }

    @GetMapping("{id}")
    public ProdutoResponse pesquisar(@PathVariable Long id) {
        return buscarProduto.Pesquisar(id);
    }
}
