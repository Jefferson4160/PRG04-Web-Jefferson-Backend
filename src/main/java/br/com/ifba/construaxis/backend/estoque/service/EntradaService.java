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

        if (entrada.getQuantidadeEntrada() == null || entrada.getQuantidadeEntrada() <= 0) {
            throw new IllegalArgumentException("A quantidade de entrada deve ser maior que zero.");
        }

        // Calcula valor total da nota
        entrada.setValorTotal(entrada.getQuantidadeEntrada() * entrada.getValorUnitario());

        // Atualiza item (saldo e preço médio)
        Item item = itemRepository.findById(entrada.getItemId())
                .orElseThrow(() -> new RuntimeException("Item não encontrado."));

        double saldoAnterior = item.getSaldoAtual() != null ? item.getSaldoAtual() : 0.0;
        double precoAnterior = item.getPrecoUnitarioMedio() != null ? item.getPrecoUnitarioMedio() : 0.0;

        double novoSaldo = saldoAnterior + entrada.getQuantidadeEntrada();

        double novoPreco =
                novoSaldo == 0
                        ? entrada.getValorUnitario()
                        : ((saldoAnterior * precoAnterior)
                        + entrada.getValorTotal()) / novoSaldo;

        item.setSaldoAtual(novoSaldo);
        item.setPrecoUnitarioMedio(novoPreco);

        itemRepository.save(item);

        return entradaRepository.save(entrada);
    }
}
