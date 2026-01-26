package br.com.ifba.construaxis.backend.estoque.repository;

import br.com.ifba.construaxis.backend.estoque.entity.Entrada;
import br.com.ifba.construaxis.backend.estoque.entity.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EntradaRepositoryTest {

    @Autowired
    private EntradaRepository entradaRepository;

    @Test
    @DisplayName("Deve salvar uma entrada com sucesso no banco de testes")
    void save_whenSuccessful() {
        // Criando o cenário
        Entrada entrada = new Entrada();
        
        // 🚨 CORREÇÃO: Usando objetos em vez de apenas UUIDs se a sua Entidade pedir Objetos
        // Se a sua classe Entrada tiver 'private Item item', use os setters abaixo:
        Item itemSimulado = new Item();
        itemSimulado.setId(UUID.randomUUID());
        
        entrada.setItem(itemSimulado); // Altere para o nome correto do campo na sua Entidade
        entrada.setDataEntrada(LocalDate.now());
        entrada.setQuantidadeEntrada(10.0);
        entrada.setValorUnitario(5.0);
        entrada.setValorTotal(50.0);

        // Ação
        Entrada saved = entradaRepository.save(entrada);

        // Verificações
        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getValorTotal()).isEqualTo(50.0);
    }
}