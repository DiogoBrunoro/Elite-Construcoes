package com.example.demo.back.service;

import com.example.demo.back.model.Fornecedor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class CustomFornecedorDetails implements UserDetails {
    private final Fornecedor fornecedor;

    public CustomFornecedorDetails(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>(); // Aqui você pode adicionar as autoridades se necessário
    }

    @Override
    public String getPassword() {
        return fornecedor.getSenhaFornecedor(); // Retorna a senha do fornecedor
    }

    @Override
    public String getUsername() {
        return fornecedor.getEmailCorporativo(); // Retorna o email como username
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

    public Fornecedor getFornecedor() {
        return fornecedor; // Método para acessar o fornecedor diretamente
    }
}
