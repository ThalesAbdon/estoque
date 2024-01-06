package br.com.estoque.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id") @ToString(of = "id")
public class Produto {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private Integer codigo;

    private Integer quantidade;

    private Integer estoqueMinimo;

    private String descricao;

    private Double precoDeCompra;

    private Double precoDeVenda;

    private Double lucro;

    @ManyToOne
    @JoinColumn(name = "fornecedor_id")
    private Fornecedor fornecedor;

    // Um produto tem vários estoques
    @OneToMany(mappedBy = "produto")
    private List<Estoque> estoques;
}
