package br.com.ifba.construaxis.backend.estoque.service;

import br.com.ifba.construaxis.backend.estoque.entity.Entrada;
import br.com.ifba.construaxis.backend.estoque.entity.Item;
import br.com.ifba.construaxis.backend.estoque.repository.EntradaRepository;
import br.com.ifba.construaxis.backend.estoque.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EntradaService {

    private final EntradaRepository entradaRepository;
    private final ItemRepository itemRepository;


    public EntradaService(EntradaRepository entradaRepository, ItemRepository itemRepository) {
        this.entradaRepository = entradaRepository;
        this.itemRepository = itemRepository;
    }

    @Transactional
    public Entrada registrarEntrada(Entrada entrada) {

        // ------------------ VALIDAÇÕES ------------------

        if (entrada.getItemId() == null) {
            throw new IllegalArgumentException("O campo itemId é obrigatório.");
        }

        if (entrada.getFornecedorId() == null) {
            throw new IllegalArgumentException("O campo fornecedorId é obrigatório.");
        }

        if (entrada.getAlmoxarifeId() == null) {
            throw new IllegalArgumentException("O campo almoxarifeId é obrigatório.");
        }

        if (entrada.getDataEntrada() == null) {
            throw new IllegalArgumentException("A data de entrada é obrigatória.");
        }

        if (entrada.getQuantidadeNf() == null || entrada.getQuantidadeNf() <= 0) {
            throw new IllegalArgumentException("A quantidade da nota fiscal deve ser maior que zero.");
        }

        if (entrada.getQuantidadeEntrada() == null || entrada.getQuantidadeEntrada() <= 0) {
            throw new IllegalArgumentException("A quantidade de entrada deve ser maior que zero.");
        }

        if (!entrada.getQuantidadeNf().equals(entrada.getQuantidadeEntrada())) {
            throw new IllegalArgumentException("A quantidade da NF deve ser igual à quantidade de entrada.");
        }

        if (entrada.getValorUnitario() == null || entrada.getValorUnitario() <= 0) {
            throw new IllegalArgumentException("O valor unitário deve ser maior que zero.");
        }

        // Converte nota fiscal vazia para null
        if (entrada.getNotaFiscalRa() != null && entrada.getNotaFiscalRa().isBlank()) {
            entrada.setNotaFiscalRa(null);
        }

        // ------------------ CÁLCULO DO VALOR TOTAL ------------------
        entrada.setValorTotal(entrada.getQuantidadeEntrada() * entrada.getValorUnitario());

        // ------------------ ATUALIZA ITEM ------------------
        Item item = itemRepository.findById(entrada.getItemId())
                .orElseThrow(() -> new RuntimeException("Item não encontrado."));

        double saldoAnterior = item.getSaldoAtual() != null ? item.getSaldoAtual() : 0.0;
        double precoAnterior = item.getPrecoUnitarioMedio() != null ? item.getPrecoUnitarioMedio() : 0.0;

        double novoSaldo = saldoAnterior + entrada.getQuantidadeEntrada();

        double novoPreco =
                novoSaldo == 0
                        ? entrada.getValorUnitario()
                        : ((saldoAnterior * precoAnterior) + entrada.getValorTotal()) / novoSaldo;

        item.setSaldoAtual(novoSaldo);
        item.setPrecoUnitarioMedio(novoPreco);

        itemRepository.save(item);

        // ------------------ SALVA A ENTRADA ------------------
        return entradaRepository.save(entrada);
    }

}
