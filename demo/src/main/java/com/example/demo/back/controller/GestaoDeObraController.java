package com.example.demo.back.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.back.model.GestaoDeObra;
import com.example.demo.back.service.GestaoDeObraService;

@RestController
@RequestMapping("/gestaoDeObra")
public class GestaoDeObraController {

    @Autowired
    private GestaoDeObraService service;

    @GetMapping
    public List<GestaoDeObra> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GestaoDeObra> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/obra/{idObra}")
    public ResponseEntity<List<GestaoDeObra>> findByObraId(@PathVariable Integer idObra) {
        List<GestaoDeObra> gestaoDeObras = service.findByObraId(idObra);
        return ResponseEntity.ok(gestaoDeObras);
    }

    @PostMapping
    public ResponseEntity<GestaoDeObra> create(@RequestBody GestaoDeObra gestaoDeObra) {
        GestaoDeObra savedGestao = service.save(gestaoDeObra);
        return ResponseEntity.ok(savedGestao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GestaoDeObra> update(@PathVariable Integer id, @RequestBody GestaoDeObra gestaoDeObra) {
        GestaoDeObra existing = service.findById(id);
        gestaoDeObra.setIdGestaoObra(existing.getIdGestaoObra());
        GestaoDeObra updatedGestao = service.save(gestaoDeObra);
        return ResponseEntity.ok(updatedGestao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
