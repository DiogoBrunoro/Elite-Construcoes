package com.example.demo.back.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "pagamento")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pagamento", unique = true)
    private Integer idPagamento;

    @Column(name = "numero_cartao", nullable = false, length = 100)
    @NotNull(message = "O Campo é obrigatório")
    private String numeroCartao;

    @Column(name = "nome_cartao", nullable = false)
    @NotNull(message = "O Campo é obrigatório")
    private String nomeCartao;

    @Column(name = "validade_cartao", nullable = false, length = 10)
    @NotNull(message = "O Campo é obrigatório")
    private String validadeCartao;

    @Column(name = "cvv", nullable = false, length = 10)
    @NotNull(message = "O Campo é obrigatório")
    private String cvv;

}