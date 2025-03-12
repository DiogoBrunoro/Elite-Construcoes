package com.example.demo.back.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.back.model.DTOs.UpdateObraDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "obra")
public class Obra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_obra", unique = true)
    private Integer idObra;

    @Column(name = "nome_da_Obra", nullable = false, length = 100)
    @NotNull(message = "O Campo é obrigatório")
    private String nomeObra;

    @Column(name = "data_inicio", nullable = false)
    private LocalDate dataInicio;

    @Column(name = "data_fim", nullable = true)
    private LocalDate dataFim;

    @Column(name = "preco", nullable = false)
    @NotNull(message = "O Campo é obrigatório")
    @Min(value = 0, message = "O valor deve ser maior ou igual a zero")
    private Double preco;

    @Column(name = "area_construida", nullable = false)
    @NotNull(message = "O Campo é obrigatório")
    private String areaConstruida;

    @Column(name = "endereco", nullable = false, length = 150)
    @NotNull(message = "O Campo é obrigatório")
    private String endereco;

    @Column(name = "observacoes_cliente", nullable = false, length = 500)
    @NotNull(message = "O Campo é obrigatório")
    private String observacaoCliente = "Sem observação";

    @Column(name = "estado", nullable = true, length = 500)
    private String estado = "Em andamento";

    @Column(name = "no_prazo", nullable = true, length = 500)
    @NotNull(message = "O Campo é obrigatório")
    private boolean noPrazo = false;

    @ManyToOne
    @JoinColumn(name = "id_servico", referencedColumnName = "id_servico", nullable = false)
    private Servico servico;

    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id", nullable = false)
    private Cliente cliente;
    
    @OneToMany(mappedBy = "obra", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<GestaoDeObra> gestaoDeObras = new ArrayList<>();  

    public Integer getIdServico() {
        return servico != null ? servico.getIdServico() : null;
    }

    public void updateFromDTO(UpdateObraDTO updateObraDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateFromDTO'");
    }
}    