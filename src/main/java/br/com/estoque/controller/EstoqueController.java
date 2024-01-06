package br.com.estoque.controller;

import br.com.estoque.controller.request.IncluirEstoqueRequest;
import br.com.estoque.service.IncluirEstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estoques")
public class EstoqueController {

    @Autowired
    private IncluirEstoqueService incluirEstoqueService;

    @PostMapping("/incluir")
    public ResponseEntity<String> incluir(@RequestBody IncluirEstoqueRequest request) {
        try {
            incluirEstoqueService.save(request);
            return ResponseEntity.status(HttpStatus.CREATED).body("Estoque adicionado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao adicionar o estoque.");
        }
    }
}
