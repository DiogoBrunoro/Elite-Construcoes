package com.example.demo.back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CombinedUserDetailsService implements UserDetailsService {

    @Autowired
    private CustomClienteDetailsService customClienteDetailsService;

    @Autowired
    private CustomFornecedorDetailsService customFornecedorDetailsService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Tente carregar primeiro como Cliente
        try {
            return customClienteDetailsService.loadUserByUsername(username);
        } catch (UsernameNotFoundException e) {
            // Se não for encontrado como Cliente, tente como Fornecedor
            return customFornecedorDetailsService.loadUserByUsername(username);
        }
    }
}
