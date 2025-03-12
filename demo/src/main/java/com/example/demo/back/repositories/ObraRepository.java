package com.example.demo.back.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.back.model.Obra;

@Repository
public interface ObraRepository extends JpaRepository<Obra, Integer> {

    // Busca obras pelo ID do fornecedor, com paginação
    @Query("SELECT o FROM Obra o WHERE o.servico.fornecedor.id = :idFornecedor")
    Page<Obra> findByFornecedorId(@Param("idFornecedor") Integer idFornecedor, Pageable pageable);

    // Busca obras pelo ID do cliente, com paginação
    @Query("SELECT o FROM Obra o WHERE o.cliente.id = :idCliente")
    Page<Obra> findByClienteId(@Param("idCliente") Integer idCliente, Pageable pageable);

    // Busca obras pelo ID do fornecedor, sem paginação
    @Query("SELECT o FROM Obra o WHERE o.servico.fornecedor.id = :idFornecedor")
    List<Obra> findAllByFornecedorId(@Param("idFornecedor") Integer idFornecedor);
}
