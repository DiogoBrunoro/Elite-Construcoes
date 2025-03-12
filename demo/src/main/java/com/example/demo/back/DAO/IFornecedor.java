package com.example.demo.back.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.back.model.Fornecedor;

public interface IFornecedor extends JpaRepository<Fornecedor, Integer>{
    Fornecedor findByEmailCorporativo(String emailCorporativo);
}
