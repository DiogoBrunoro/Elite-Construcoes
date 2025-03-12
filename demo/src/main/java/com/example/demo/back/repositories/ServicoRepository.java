package com.example.demo.back.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.back.model.Servico;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Integer>, JpaSpecificationExecutor<Servico> {

    // Consulta personalizada para buscar o serviço pelo idServico
    @Query("SELECT s FROM Servico s WHERE s.idServico = :idServico")
    Optional<Servico> findByIdServico(@Param("idServico") Integer idServico);

    // Método para deletar o serviço pelo idServico
    void deleteByIdServico(Integer idServico);

     // Método para buscar todos os serviços de um fornecedor específico
     @Query("SELECT s FROM Servico s WHERE s.fornecedor.id = :idFornecedor")
     List<Servico> findByFornecedorId(@Param("idFornecedor") Integer idFornecedor);
}


