package com.example.demo.back.service;

import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.back.infra.security.TokenService;
import com.example.demo.back.model.Fornecedor;
import com.example.demo.back.repositories.FornecedorRepository;

import jakarta.transaction.Transactional;
import lombok.NonNull;

@Service
public class FornecedorService implements UserDetailsService {
    
    @Autowired
    private FornecedorRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;

   
    public Fornecedor findById(@NonNull Integer id) {
        Optional<Fornecedor> usuario = this.usuarioRepository.findById(id);
        return usuario.orElseThrow(() -> new ObjectNotFoundException(id, "Fornecedor não encontrado!"));
    }

    @Transactional
    public Fornecedor create(@NonNull Fornecedor usuario) {
        return this.usuarioRepository.save(usuario);
    }

    @Transactional
public Fornecedor update(@NonNull Fornecedor fornecedor) {
    // Encontrar o fornecedor existente pelo ID
    Fornecedor existingFornecedor = findById(fornecedor.getIdFornecedor());

    // Atualizar os campos, se não forem nulos
    if (fornecedor.getNomeFornecedor() != null) {
        existingFornecedor.setNomeFornecedor(fornecedor.getNomeFornecedor());
    }
    if (fornecedor.getCnpj() != null) {
        existingFornecedor.setCnpj(fornecedor.getCnpj());
    }
    if (fornecedor.getEndereco() != null) {
        existingFornecedor.setEndereco(fornecedor.getEndereco());
    }
    if (fornecedor.getEmailCorporativo() != null) {
        existingFornecedor.setEmailCorporativo(fornecedor.getEmailCorporativo());
    }
    if (fornecedor.getSenhaFornecedor() != null) {
        existingFornecedor.setSenhaFornecedor(fornecedor.getSenhaFornecedor());
    }

    // Salvar o fornecedor atualizado
    return usuarioRepository.save(existingFornecedor);
}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Fornecedor usuario = usuarioRepository.findByEmailCorporativo(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado: " + username);
        }
        return usuario;
    }



    @Transactional
    public void delete(@NonNull Integer id) { // Padronizei para Integer ao invés de Long
        Fornecedor usuario = findById(id);
        usuarioRepository.delete(usuario);
    }

    public Fornecedor getUserByToken(String token) {
        String email = tokenService.validateToken(token);
        if (email.isEmpty()) {
            return null;
        }

        return usuarioRepository.findByEmailCorporativo(email);
    }
}
