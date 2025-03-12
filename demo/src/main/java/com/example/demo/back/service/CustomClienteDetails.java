package com.example.demo.back.service;

import com.example.demo.back.model.Cliente;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class CustomClienteDetails implements UserDetails {
    private final Cliente cliente;

    public CustomClienteDetails(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>(); // Aqui você pode adicionar as autoridades se necessário
    }

    @Override
    public String getPassword() {
        return cliente.getSenha(); // Retorna a senha do cliente
    }

    @Override
    public String getUsername() {
        return cliente.getEmail(); // Retorna o email como username
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Lógica para verificar se a conta não expirou
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Lógica para verificar se a conta não está bloqueada
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Lógica para verificar se as credenciais não expiraram
    }

    @Override
    public boolean isEnabled() {
        return true; // Lógica para verificar se a conta está habilitada
    }

    public Cliente getCliente() {
        return cliente; // Método para acessar o cliente diretamente
    }
}
