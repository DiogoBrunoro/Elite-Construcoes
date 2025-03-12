package com.example.demo.back.model.DTOs;

import java.time.LocalDate;

public record CreateObraDTO(String nomeObra, LocalDate dataInicio, Double preco, String areaConstruida, String endereco, String observacaoCliente, Integer servico, Integer cliente) {
    
}

