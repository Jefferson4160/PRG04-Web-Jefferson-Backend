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

    // Injeção de Dependência
    public EntradaService(EntradaRepository entradaRepository, ItemRepository itemRepository) {
        this.entradaRepository = entradaRepository;
        this.itemRepository = itemRepository;
    }

    @Transactional
    public Entrada registrarEntrada(Entrada entrada) throws Exception {

        // 1. Validação
        if (entrada.getQuantidadeEntrada() == null || entrada.getQuantidadeEntrada() <= 0) {
            throw new IllegalArgumentException("A quantidade de entrada deve ser maior que zero.");
        }
        if (entrada.getItemId() == null || entrada.getFornecedorId() == null) {
            throw new IllegalArgumentException("Item e Fornecedor são obrigatórios.");
        }

        // Calcula o valor total
        entrada.setValorTotal(entrada.getQuantidadeEntrada() * entrada.getValorUnitario());

        // 2. Buscar o Item para Atualização
        Item item = itemRepository.findById(entrada.getItemId())
                .orElseThrow(() -> new RuntimeException("Item não encontrado: " + entrada.getItemId()));

        // 3. Implementar a Lógica de Estoque (Cálculo de Saldo e Preço Médio)
        Double oldSaldo = item.getSaldoAtual() != null ? item.getSaldoAtual() : 0.0;
        Double oldPreco = item.getPrecoUnitarioMedio() != null ? item.getPrecoUnitarioMedio() : 0.0;
        Double valorNovaEntrada = entrada.getQuantidadeEntrada() * entrada.getValorUnitario();

        Double novoSaldo = oldSaldo + entrada.getQuantidadeEntrada();
        Double novoPreco = 0.0;

        if (novoSaldo > 0) {
            // Formula do Preço Médio Ponderado:
            novoPreco = ((oldSaldo * oldPreco) + valorNovaEntrada) / novoSaldo;
        }

        // 4. Atualizar o Item
        item.setSaldoAtual(novoSaldo);
        item.setPrecoUnitarioMedio(novoPreco);
        itemRepository.save(item);

        // 5. Salvar o Registro de Entrada
        return entradaRepository.save(entrada);
    }
}
