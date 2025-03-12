package com.example.demo.back.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.back.model.GestaoDeObra;

@Repository
public interface GestaoDeObraRepository extends JpaRepository<GestaoDeObra, Integer>, JpaSpecificationExecutor<GestaoDeObra> {

    @Query("SELECT g FROM GestaoDeObra g WHERE g.obra.idObra = :idObra")
    List<GestaoDeObra> findByObraId(@Param("idObra") Integer idObra);
}

