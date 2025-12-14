package br.com.ifba.construaxis.backend.estoque.service;

import br.com.ifba.construaxis.backend.estoque.entity.Entrada;
import br.com.ifba.construaxis.backend.estoque.entity.Item;
import br.com.ifba.construaxis.backend.estoque.repository.EntradaRepository;
import br.com.ifba.construaxis.backend.estoque.repository.ItemRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class EntradaServiceTest {

    @Autowired
    private EntradaService entradaService;

    @Autowired
    private EntradaRepository entradaRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    @DisplayName("Deve lançar exceção quando quantidadeEntrada for inválida")
    void registrarEntrada_whenQuantidadeInvalida_thenThrowsException() {

        Entrada entrada = new Entrada();
        entrada.setQuantidadeEntrada(0.0);

        assertThatThrownBy(() -> entradaService.registrarEntrada(entrada))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("quantidade de entrada");
    }

    @Test
    @DisplayName("Deve registrar entrada com sucesso")
    void registrarEntrada_whenValid_thenSuccess() {

        Item item = new Item();
        item.setSaldoAtual(0.0);
        item.setPrecoUnitarioMedio(0.0);
        itemRepository.save(item);

        Entrada entrada = new Entrada();
        entrada.setItemId(item.getId());
        entrada.setFornecedorId(UUID.randomUUID());
        entrada.setAlmoxarifeId(UUID.randomUUID());
        entrada.setDataEntrada(LocalDate.now());
        entrada.setQuantidadeNf(10.0);
        entrada.setQuantidadeEntrada(10.0);
        entrada.setValorUnitario(2.0);

        Entrada saved = entradaService.registrarEntrada(entrada);

        assertThat(saved.getValorTotal()).isEqualTo(20.0);
    }
}
