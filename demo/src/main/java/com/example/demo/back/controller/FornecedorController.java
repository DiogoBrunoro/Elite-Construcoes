package com.example.demo.back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.back.infra.security.TokenService;
import com.example.demo.back.model.DTOs.FornecedorAuthenticationDTO;
import com.example.demo.back.model.DTOs.FornecedorRegisterDTO;
import com.example.demo.back.model.DTOs.LoginResponseDTO;
import com.example.demo.back.model.DTOs.TokenDTO;
import com.example.demo.back.model.Fornecedor;
import com.example.demo.back.repositories.FornecedorRepository;
import com.example.demo.back.service.CustomFornecedorDetails;
import com.example.demo.back.service.FornecedorService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/fornecedor")
@Validated
public class FornecedorController {

    @Autowired
    private FornecedorService fornecedorService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Autowired
    private TokenService tokenService;

    @GetMapping("/{id}")
    public ResponseEntity<Fornecedor> findById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.fornecedorService.findById(id));
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid FornecedorAuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.emailCorporativo(), data.senhaFornecedor());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        // Obtém o fornecedor autenticado
        var customFornecedorDetails = (CustomFornecedorDetails) auth.getPrincipal(); // Cast para CustomFornecedorDetails
        var fornecedor = customFornecedorDetails.getFornecedor(); // Obtém o fornecedor a partir do CustomFornecedorDetails

        if (fornecedor != null) {
            var token = tokenService.generateToken(fornecedor);
            System.out.println("Fornecedor autenticado com ID: " + fornecedor.getIdFornecedor()); // Log para verificar o ID
            return ResponseEntity.ok(new LoginResponseDTO(token, fornecedor.getIdFornecedor()));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid FornecedorRegisterDTO data) {
        if (this.fornecedorRepository.findByEmailCorporativo(data.emailCorporativo()) != null) {
            return ResponseEntity.badRequest().build();
        }
        String senhaCriptografada = new BCryptPasswordEncoder().encode(data.senhaFornecedor());

        Fornecedor novoFornecedor = new Fornecedor(
                null,
                data.cnpj(),
                data.nomeFornecedor(),
                data.endereco(),
                data.emailCorporativo(),
                senhaCriptografada
        );

        this.fornecedorService.create(novoFornecedor);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fornecedor> update(@PathVariable("id") Integer id, @Valid @RequestBody Fornecedor fornecedor) {
        fornecedor.setIdFornecedor(id);

        // Criptografar a nova senha antes de atualizar
        if (fornecedor.getSenhaFornecedor() != null && !fornecedor.getSenhaFornecedor().isEmpty()) {
            String senhaCriptografada = new BCryptPasswordEncoder().encode(fornecedor.getSenhaFornecedor());
            fornecedor.setSenhaFornecedor(senhaCriptografada);
        }

        this.fornecedorService.update(fornecedor);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) {
        this.fornecedorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/tipoUser")
    public ResponseEntity<Fornecedor> getUserByToken(@RequestBody @Valid TokenDTO tokenDTO) {
        String authHeader = tokenDTO.token();
        if (authHeader == null || authHeader.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        String token = authHeader.replace("Bearer ", "");
        Fornecedor fornecedor = fornecedorService.getUserByToken(token);
        if (fornecedor == null) {
            throw new RuntimeException("Token inválido, faça login novamente");
        }
        return ResponseEntity.ok(fornecedor);
    }
}
