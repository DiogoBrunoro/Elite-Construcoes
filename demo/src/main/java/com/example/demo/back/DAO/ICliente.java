package com.example.demo.back.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.back.model.Cliente;

public interface ICliente extends JpaRepository<Cliente, Integer>{
    Cliente findByEmail(String email);
         
}
