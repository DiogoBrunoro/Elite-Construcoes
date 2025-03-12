package com.example.demo.back.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.back.model.DTOs.UpdateObraDTO;
import com.example.demo.back.model.GestaoDeObra;
import com.example.demo.back.model.Obra;
import com.example.demo.back.repositories.ObraRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ObraService {

    @Autowired
    private ObraRepository obraRepository;

    // Método para encontrar uma obra pelo ID
    public Obra findById(Integer id) {
        return obraRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Obra não encontrada com ID: " + id));
    }

    // Método para criar uma nova obra
    @Transactional
    public Obra create(Obra obra) {
        return obraRepository.save(obra);
    }

    // Método para atualizar uma obra existente
    @Transactional
    public void updateObraById(Integer id, UpdateObraDTO updateObraDTO) {
        Obra obra = findById(id);  // Verifica se a obra existe
        
        // Atualiza os campos com os dados do DTO
        obra.setNomeObra(updateObraDTO.nomeObra());
        obra.setDataInicio(updateObraDTO.dataInicio());
        obra.setPreco(updateObraDTO.preco());
        obra.setAreaConstruida(updateObraDTO.areaConstruida());
        obra.setEndereco(updateObraDTO.endereco());
        obra.setObservacaoCliente(updateObraDTO.observacaoCliente());
    
        // Adiciona a atualização do campo data_fim
        if (updateObraDTO.dataFim() != null) {
            obra.setDataFim(updateObraDTO.dataFim());
        }
    
        obraRepository.save(obra);  
    }
    

    @Transactional
public void updateEstado(Integer id, String estado) {
    Obra obra = findById(id);
    obra.setEstado(estado);
    obraRepository.save(obra); // Persistência explícita
}

@Transactional
public void updateNoPrazo(Integer id, Boolean noPrazo) {
    Obra obra = findById(id);
    obra.setNoPrazo(noPrazo);
    obraRepository.save(obra); // Persistência explícita
}
    

    // Método para excluir uma obra pelo ID
   @Transactional
public void deleteById(Integer id) {
    if (!obraRepository.existsById(id)) {
        throw new EntityNotFoundException("Obra não encontrada com ID: " + id);
    }

    // Recupera a obra antes da exclusão
    Obra obra = obraRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Obra não encontrada com ID: " + id));

    // Atualiza as referências em GestaoDeObra
    for (GestaoDeObra gestao : obra.getGestaoDeObras()) {
        gestao.setObra(null); // Define a obra como null
    }

    // Exclui a obra
    obraRepository.deleteById(id);
}

    // Método para listar todas as obras com paginação
    public Page<Obra> findAllPageable(Pageable pageable) {
        return obraRepository.findAll(pageable);
    }

    // Buscar obras por fornecedor com paginação
    public Page<Obra> findByFornecedorId(Integer idFornecedor, Pageable pageable) {
        return obraRepository.findByFornecedorId(idFornecedor, pageable);
    }

    // Buscar obras por cliente com paginação
    public Page<Obra> findByClienteId(Integer idCliente, Pageable pageable) {
        return obraRepository.findByClienteId(idCliente, pageable);
    }

    public List<Obra> findByFornecedorIdSemPaginacao(Integer idFornecedor) {
        return obraRepository.findAllByFornecedorId(idFornecedor);
    }
}

