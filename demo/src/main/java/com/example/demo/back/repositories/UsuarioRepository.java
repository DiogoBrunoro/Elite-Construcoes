package com.example.demo.back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.back.model.Cliente;

public interface UsuarioRepository extends JpaRepository<Cliente, Integer> {
    Cliente findByEmail(String email); 
}
