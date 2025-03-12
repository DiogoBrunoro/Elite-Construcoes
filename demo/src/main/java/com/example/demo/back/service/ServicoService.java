package com.example.demo.back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.back.model.DTOs.UpdateServiceDTO;
import com.example.demo.back.model.Servico;
import com.example.demo.back.repositories.ServicoRepository;
import com.example.demo.back.service.exceptions.ObjectNotFoundException;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.NonNull;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository servicoRepository;

    public Servico findById(@NonNull Integer idServico) {
        Optional<Servico> servico = this.servicoRepository.findById(idServico);
        return servico.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + idServico + ", Tipo: " + Servico.class.getName()));
    }

    public List<Servico> findByFornecedorId(Integer idFornecedor) {
        return servicoRepository.findByFornecedorId(idFornecedor);
    }

    @Transactional
    public Servico create(@NonNull Servico servico) {
        return this.servicoRepository.save(servico);
    }

    @Transactional
    public Servico updateServiceById(Integer id, UpdateServiceDTO updateServiceDTO) {
        // Encontrar o serviço existente pelo ID
        Servico servicoExistente = servicoRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Serviço não encontrado com ID: " + id));
    
        // Atualizar os campos apenas se os valores não forem nulos

         if (updateServiceDTO.nomeServico() != null) {
            servicoExistente.setNomeServico(updateServiceDTO.nomeServico());
        }
        if (updateServiceDTO.preco() != null) {
            servicoExistente.setPreco(updateServiceDTO.preco());
        }
        if (updateServiceDTO.unidadeMedida() != null) {
            servicoExistente.setUnidadeMedida(updateServiceDTO.unidadeMedida());
        }
        if (updateServiceDTO.descricao() != null) {
            servicoExistente.setDescricao(updateServiceDTO.descricao());
        }
    
        // Salvar e retornar o serviço atualizado
        return servicoRepository.save(servicoExistente);
    }

    @Transactional
    public void deleteById(Integer id) {
        // Verifica se o serviço existe
        servicoRepository.deleteById(id);
    }

    public Page<Servico> findAllPageable(Pageable pageable) {
        return servicoRepository.findAll(pageable);
    }
}