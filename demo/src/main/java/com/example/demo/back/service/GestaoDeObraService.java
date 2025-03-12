package com.example.demo.back.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.back.model.GestaoDeObra;
import com.example.demo.back.repositories.GestaoDeObraRepository;

@Service
public class GestaoDeObraService {

    @Autowired
    private GestaoDeObraRepository repository;

    public List<GestaoDeObra> findAll() {
        return repository.findAll();
    }

    public GestaoDeObra findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Gestão de Obra não encontrada!"));
    }

    public List<GestaoDeObra> findByObraId(Integer idObra) {
        return repository.findByObraId(idObra);
    }

    public GestaoDeObra save(GestaoDeObra gestaoDeObra) {
        return repository.save(gestaoDeObra);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
