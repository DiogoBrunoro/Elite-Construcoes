package com.example.demo.back.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.back.model.Cliente;
import com.example.demo.back.model.DTOs.CreateObraDTO;
import com.example.demo.back.model.DTOs.UpdateEstadoDTO;
import com.example.demo.back.model.DTOs.UpdateNoPrazoDTO;
import com.example.demo.back.model.DTOs.UpdateObraDTO;
import com.example.demo.back.model.Obra;
import com.example.demo.back.model.Servico;
import com.example.demo.back.repositories.ClienteRepository;
import com.example.demo.back.repositories.FornecedorRepository;
import com.example.demo.back.repositories.ServicoRepository;
import com.example.demo.back.service.ObraService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/obra")
public class ObraController {

    @Autowired
    private ObraService obraService;

    @Autowired
    private ServicoRepository servicoRepository;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    // Buscar obras por fornecedor com paginação
    @GetMapping("/fornecedor/{idFornecedor}")
    public ResponseEntity<Page<Obra>> getObrasByFornecedor(
            @PathVariable Integer idFornecedor,
            @PageableDefault(size = 10, sort = "nomeObra") Pageable pageable) {
        
        // Validação: Verificar se o fornecedor existe
        fornecedorRepository.findById(idFornecedor)
                .orElseThrow(() -> new EntityNotFoundException("Fornecedor não encontrado com ID: " + idFornecedor));

        // Buscar obras por fornecedor com paginação
        Page<Obra> obras = obraService.findByFornecedorId(idFornecedor, pageable);
        return ResponseEntity.ok(obras);
    }

    // Buscar obras por cliente com paginação
    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<Page<Obra>> getObrasByCliente(
            @PathVariable Integer idCliente,
            @PageableDefault(size = 10, sort = "nomeObra") Pageable pageable) {
        
        // Validação: Verificar se o cliente existe
        clienteRepository.findById(idCliente)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com ID: " + idCliente));

        // Buscar obras por cliente com paginação
        Page<Obra> obras = obraService.findByClienteId(idCliente, pageable);
        return ResponseEntity.ok(obras);
    }

    // Buscar todas as obras com paginação
    @GetMapping("/{id}")
public ResponseEntity<Obra> getObraById(@PathVariable Integer id) {
    Obra obra = obraService.findById(id);
    return ResponseEntity.ok(obra);
}
    // Criar uma nova obra
    @PostMapping
public ResponseEntity<Obra> create(@RequestBody @Valid CreateObraDTO createObraDTO) {
    // Construção do objeto Obra a partir do DTO
    Obra obra = new Obra();
    obra.setNomeObra(createObraDTO.nomeObra());
    obra.setDataInicio(createObraDTO.dataInicio());
    obra.setPreco(createObraDTO.preco());
    obra.setAreaConstruida(createObraDTO.areaConstruida());
    obra.setEndereco(createObraDTO.endereco());
    obra.setObservacaoCliente(createObraDTO.observacaoCliente());

    // Carregar o serviço associado, se o ID for fornecido
    if (createObraDTO.servico() != null) {
        Servico servico = servicoRepository.findById(createObraDTO.servico())
            .orElseThrow(() -> new EntityNotFoundException("Serviço não encontrado com ID: " + createObraDTO.servico()));
        obra.setServico(servico);
    }

    // Carregar o cliente associado, se o ID for fornecido
    if (createObraDTO.cliente() != null) {
        Cliente cliente = clienteRepository.findById(createObraDTO.cliente())
            .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com ID: " + createObraDTO.cliente()));
        obra.setCliente(cliente);
    }

    // Salva a obra criada e retorna a URI de criação
    Obra obraSalva = obraService.create(obra);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(obraSalva.getIdObra()).toUri();
    return ResponseEntity.created(uri).body(obraSalva);
}


    // Atualizar uma obra existente
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody UpdateObraDTO updateObraDTO) {
        obraService.updateObraById(id, updateObraDTO);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/estado")
public ResponseEntity<Void> atualizarEstado(
        @PathVariable Integer id,
        @RequestBody UpdateEstadoDTO updateEstadoDTO) {
    obraService.updateEstado(id, updateEstadoDTO.estado());
    return ResponseEntity.noContent().build();
}

@PutMapping("/{id}/noPrazo")
public ResponseEntity<Void> atualizarNoPrazo(
        @PathVariable Integer id,
        @RequestBody UpdateNoPrazoDTO updateNoPrazoDTO) {
    obraService.updateNoPrazo(id, updateNoPrazoDTO.noPrazo());
    return ResponseEntity.noContent().build();
}

@GetMapping("/fornecedor/indicador/{id}")
public ResponseEntity<List<Obra>> listarTodasObrasPorFornecedor(@PathVariable Integer id) {
    List<Obra> obras = obraService.findByFornecedorIdSemPaginacao(id);
    return ResponseEntity.ok(obras);
}

    // Excluir uma obra pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        obraService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
