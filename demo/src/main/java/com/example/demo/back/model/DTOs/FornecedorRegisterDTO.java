package com.example.demo.back.model.DTOs;

public record FornecedorRegisterDTO(
    String emailCorporativo,
    String senhaFornecedor,
    String nomeFornecedor,
    String cnpj,
    String endereco
) {
}
