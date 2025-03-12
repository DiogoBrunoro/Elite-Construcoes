package com.example.demo.back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.back.model.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Integer> {
    Fornecedor findByEmailCorporativo(String emailCorpotativo);
}