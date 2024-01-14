package br.com.estoque.controller;

import br.com.estoque.controller.request.ComprarLoteRequest;
import br.com.estoque.controller.request.IncluirEstoqueRequest;
import br.com.estoque.service.BuscarEstoquesQuantidadeBaixa;
import br.com.estoque.service.BuscarEstoquesVencidos;
import br.com.estoque.service.ComprarLoteService;
import br.com.estoque.service.IncluirEstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/estoques")
public class EstoqueController {

    @Autowired
    private IncluirEstoqueService incluirEstoqueService;

    @Autowired
    private BuscarEstoquesQuantidadeBaixa estoquesQuantidadeBaixa;
    @Autowired
    private BuscarEstoquesVencidos buscarEstoquesVencidos;
    @Autowired
    private ComprarLoteService comprarLoteService;

    @PostMapping("/incluir")
    public ResponseEntity<String> incluir(@RequestBody IncluirEstoqueRequest request) {
        try {
            incluirEstoqueService.save(request);
            return ResponseEntity.status(HttpStatus.CREATED).body("Estoque adicionado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao adicionar o estoque.");
        }
    }
    @GetMapping("/estoque-baixo")
    public ResponseEntity<List<Map<String, Object>>> getFornecedoresComEstoqueBaixo() {
        List<Map<String, Object>> response = estoquesQuantidadeBaixa.getFornecedoresComEstoqueBaixo();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/estoque-vencido")
    public ResponseEntity<List<Map<String, Object>>> getFornecedoresComEstoqueVencidos() {
        List<Map<String, Object>> response = buscarEstoquesVencidos.getFornecedoresComEstoqueVencido();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/comprar-lote")
    public void comprarLote(@RequestBody ComprarLoteRequest request) {
        comprarLoteService.comprar(request);
    }
}
