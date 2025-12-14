package br.com.ifba.construaxis.backend.estoque.repository;

import br.com.ifba.construaxis.backend.estoque.entity.Entrada;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@EnableJpaRepositories
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class EntradaRepositoryTest {

    @Autowired
    private EntradaRepository entradaRepository;

    @Test
    @DisplayName("Deve salvar uma entrada com sucesso")
    void save_whenSuccessful() {

        Entrada entrada = new Entrada();
        entrada.setItemId(UUID.randomUUID());
        entrada.setFornecedorId(UUID.randomUUID());
        entrada.setAlmoxarifeId(UUID.randomUUID());
        entrada.setDataEntrada(LocalDate.now());
        entrada.setQuantidadeNf(10.0);
        entrada.setQuantidadeEntrada(10.0);
        entrada.setValorUnitario(5.0);
        entrada.setValorTotal(50.0);

        Entrada saved = entradaRepository.save(entrada);

        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getValorTotal()).isEqualTo(50.0);
    }
}
