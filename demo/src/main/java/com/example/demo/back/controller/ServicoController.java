package com.example.demo.back.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.back.model.DTOs.CreateServicoDTO;
import com.example.demo.back.model.DTOs.UpdateServiceDTO;
import com.example.demo.back.model.Fornecedor;
import com.example.demo.back.model.Servico;
import com.example.demo.back.repositories.FornecedorRepository;
import com.example.demo.back.service.ServicoService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/servico")
@Validated
public class ServicoController {

    @Autowired
    private ServicoService servicoService;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    // Buscar serviço por ID
    @GetMapping("/{id}")
    public ResponseEntity<Servico> findById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.servicoService.findById(id));
    }

    @GetMapping("/fornecedor/{idFornecedor}")
    public ResponseEntity<List<Servico>> findByFornecedorId(@PathVariable Integer idFornecedor) {
        List<Servico> servicos = servicoService.findByFornecedorId(idFornecedor);
        return ResponseEntity.ok(servicos);
    }

    // Criar novo serviço
   @PostMapping
public ResponseEntity<Servico> create(@RequestBody @Valid CreateServicoDTO createServicoDTO) {
    // Criar uma nova instância de Servico a partir dos dados do DTO
    Servico servico = new Servico();
    servico.setNomeServico(createServicoDTO.nomeServico());
    servico.setPreco(createServicoDTO.preco());
    servico.setUnidadeMedida(createServicoDTO.unidadeMedida());
    servico.setDescricao(createServicoDTO.descricao());

    // Carregar o fornecedor associado, se o ID for fornecido
    if (createServicoDTO.idFornecedor() != null) {
        Fornecedor fornecedor = fornecedorRepository.findById(createServicoDTO.idFornecedor())
                .orElseThrow(() -> new EntityNotFoundException("Fornecedor não encontrado com ID: " + createServicoDTO.idFornecedor()));
        servico.setFornecedor(fornecedor);
    }

    // Salva o serviço no banco de dados
    Servico novoServico = servicoService.create(servico);

    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(novoServico.getIdServico()).toUri();
    return ResponseEntity.created(uri).body(novoServico);

}


    // Atualizar serviço existente
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateServiceById(@PathVariable("id") String id,
            @Valid @RequestBody UpdateServiceDTO updateServiceDTO) {
        // Converter a String para Integer
        Integer serviceId = Integer.valueOf(id); // ou usar Integer.parseInt(id)

        // Chama o serviço para atualizar o serviço pelo ID
        servicoService.updateServiceById(serviceId, updateServiceDTO);

        // Retorna 204 No Content após a atualização
        return ResponseEntity.noContent().build();
    }

    // Deletar serviço por ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) {
        servicoService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // Buscar todos os serviços com paginação
    @GetMapping
    public List<Servico> findAllPageable(Pageable pageable) {
        return servicoService.findAllPageable(pageable).getContent();
    }
}