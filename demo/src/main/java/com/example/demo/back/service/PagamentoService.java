package com.example.demo.back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.back.model.Pagamento;
import com.example.demo.back.repositories.PagamentoRepository;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    // Método para criar um novo pagamento
    @Transactional
    public Pagamento salvarPagamento(Pagamento pagamento) {
        return pagamentoRepository.save(pagamento);
    }

    // Método para obter todos os pagamentos
    public List<Pagamento> listarPagamentos() {
        return pagamentoRepository.findAll();
    }

    // Método para obter um pagamento pelo ID
    public Optional<Pagamento> buscarPagamentoPorId(Integer idPagamento) {
        return pagamentoRepository.findById(idPagamento);
    }

    // Método para atualizar um pagamento existente
    @Transactional
    public Pagamento atualizarPagamento(Integer idPagamento, Pagamento pagamentoAtualizado) {
        Pagamento pagamento = pagamentoRepository.findById(idPagamento)
                .orElseThrow(() -> new RuntimeException("Pagamento não encontrado com ID: " + idPagamento));
        pagamento.setNumeroCartao(pagamentoAtualizado.getNumeroCartao());
        pagamento.setNomeCartao(pagamentoAtualizado.getNomeCartao());
        pagamento.setValidadeCartao(pagamentoAtualizado.getValidadeCartao());
        pagamento.setCvv(pagamentoAtualizado.getCvv());
        return pagamentoRepository.save(pagamento);
    }

    // Método para deletar um pagamento pelo ID
    @Transactional
    public void deletarPagamento(Integer idPagamento) {
        pagamentoRepository.deleteById(idPagamento);
    }
}
