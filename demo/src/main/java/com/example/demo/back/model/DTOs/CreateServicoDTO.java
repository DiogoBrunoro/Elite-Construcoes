package com.example.demo.back.model.DTOs;

public record CreateServicoDTO(String nomeServico, Double preco, String unidadeMedida, String descricao, Integer idFornecedor) {
    
}