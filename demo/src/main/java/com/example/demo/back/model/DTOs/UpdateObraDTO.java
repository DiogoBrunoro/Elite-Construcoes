package com.example.demo.back.model.DTOs;

import java.time.LocalDate;

public record  UpdateObraDTO (String nomeObra,LocalDate dataInicio,LocalDate dataFim, Double preco, String areaConstruida,String endereco, String observacaoCliente, String estado, Boolean noPrazo) {
}
