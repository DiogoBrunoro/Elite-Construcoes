package com.example.demo.back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.back.model.Fornecedor;
import com.example.demo.back.service.CustomFornecedorDetails; // Importando a nova classe
import com.example.demo.back.repositories.FornecedorRepository; // Supondo que você tenha um repositório para Fornecedor

@Service
public class CustomFornecedorDetailsService implements UserDetailsService {

    @Autowired
    private FornecedorRepository fornecedorRepository; // Repositório para fornecedores

    @Override
    public UserDetails loadUserByUsername(String emailCorporativo) throws UsernameNotFoundException {
        Fornecedor fornecedor = fornecedorRepository.findByEmailCorporativo(emailCorporativo); // Método para buscar pelo email

        if (fornecedor == null) {
            throw new UsernameNotFoundException("Fornecedor não encontrado: " + emailCorporativo);
        }

        return new CustomFornecedorDetails(fornecedor); // Retorna uma instância de CustomFornecedorDetails
    }
}
