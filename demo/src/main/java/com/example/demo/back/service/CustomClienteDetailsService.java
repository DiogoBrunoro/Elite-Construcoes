package com.example.demo.back.service;

import java.util.ArrayList;

import com.example.demo.back.model.Fornecedor;
import com.example.demo.back.repositories.ClienteRepository;
import com.example.demo.back.repositories.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.back.model.Cliente;
import com.example.demo.back.repositories.UsuarioRepository;

@Service
public class CustomClienteDetailsService implements UserDetailsService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Cliente cliente = clienteRepository.findByEmail(email); // Método para buscar pelo email

        if (cliente == null) {
            throw new UsernameNotFoundException("Cliente não encontrado: " + email);
        }

        return new CustomClienteDetails(cliente);
    }
}

