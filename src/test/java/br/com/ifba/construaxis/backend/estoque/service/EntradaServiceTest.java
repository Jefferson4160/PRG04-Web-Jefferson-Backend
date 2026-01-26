package br.com.ifba.construaxis.backend.estoque.service;

import br.com.ifba.construaxis.backend.estoque.entity.Entrada;
import br.com.ifba.construaxis.backend.estoque.entity.Item;
import br.com.ifba.construaxis.backend.estoque.repository.ItemRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional // 🛡️ Essencial: limpa o banco de testes após cada execução
class EntradaServiceTest {

    @Autowired
    private EntradaService entradaService;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    @DisplayName("Deve lançar exceção quando a quantidade for zero")
    void registrarEntrada_whenQuantidadeInvalida_thenThrowsException() {
        Entrada entrada = new Entrada();
        entrada.setQuantidadeEntrada(0.0);

        assertThatThrownBy(() -> entradaService.registrarEntrada(entrada))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("quantidade de entrada");
    }

    @Test
    @DisplayName("Deve registrar entrada e atualizar o saldo do item no almoxarifado")
    void registrarEntrada_whenValid_thenSuccess() {
        // 1. Criar e salvar um item inicial no banco de teste
        Item item = new Item();
        item.setSaldoAtual(10.0);
        item.setPrecoUnitarioMedio(10.0);
        item = itemRepository.save(item);

        // 2. Preparar a entrada de 5 unidades a 20.0 cada
        Entrada entrada = new Entrada();
        entrada.setItem(item); 
        entrada.setQuantidadeEntrada(5.0);
        entrada.setValorUnitario(20.0);
        entrada.setDataEntrada(LocalDate.now());

        // 3. Executar o serviço
        Entrada saved = entradaService.registrarEntrada(entrada);

        // 4. Validar os cálculos
        assertThat(saved.getValorTotal()).isEqualTo(100.0);
        
        // 5. Validar se o saldo do item foi atualizado (10 + 5 = 15)
        Item itemNoBanco = itemRepository.findById(item.getId()).orElseThrow();
        assertThat(itemNoBanco.getSaldoAtual()).isEqualTo(15.0);
    }
}