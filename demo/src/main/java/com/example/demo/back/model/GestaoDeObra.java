package com.example.demo.back.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "gestao_de_obra")
@Data  // Lombok gera getters, setters, equals, hashCode, toString
public class GestaoDeObra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_gestao_obra")
    private Integer idGestaoObra;

    @Column(name = "data_atualizacao")
    private Date dataAtualizacao;
    
    @Column(name = "barra_progresso")
    private double barraProgresso;
    
    @Column(name = "comentario")
    private String comentario;

    @ManyToOne
    @JoinColumn(name = "id_obra", referencedColumnName = "id_obra", updatable = false)
    private Obra obra;
}
