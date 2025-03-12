package com.example.demo.back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.back.model.Cliente;





@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Integer> {

    Cliente findByEmail(String email);

}