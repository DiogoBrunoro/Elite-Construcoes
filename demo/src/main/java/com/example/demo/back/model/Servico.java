package com.example.demo.back.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "servico")
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servico", unique = true)
    private Integer idServico;

    @Column(name = "nome_do_servico", nullable = false, length = 100)
    @NotNull(message = "O Campo é obrigatório")
    private String nomeServico;

    @Column(name = "preco", nullable = false)
    @NotNull(message = "O Campo é obrigatório")
    @Min(value = 0, message = "O valor deve ser maior ou igual a zero")
    private Double preco;

    @Column(name = "unidade_de_medida", nullable = false, length = 10)
    @NotNull(message = "O Campo é obrigatório")
    private String unidadeMedida;

    @Column(name = "descricao", nullable = false, length = 500)
    @NotNull(message = "O Campo é obrigatório")
    private String descricao;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "servico")
    private List<Obra> obras = new ArrayList<>();
    

    @ManyToOne
    @JoinColumn(name = "id_fornecedor", referencedColumnName = "id_fornecedor", nullable = true)
    private Fornecedor fornecedor;

    public Servico(String nomeServico, Double preco, String unidadeMedida, String descricao, Fornecedor fornecedor) {
        this.nomeServico = nomeServico;
        this.preco = preco;
        this.unidadeMedida = unidadeMedida;
        this.descricao = descricao;
        this.fornecedor = fornecedor;
    }
}

