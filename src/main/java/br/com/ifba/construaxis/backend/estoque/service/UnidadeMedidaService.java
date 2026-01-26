package br.com.ifba.construaxis.backend.estoque.service;

import br.com.ifba.construaxis.backend.estoque.entity.UnidadeMedida;
import br.com.ifba.construaxis.backend.estoque.repository.UnidadeMedidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class UnidadeMedidaService {

    @Autowired
    private UnidadeMedidaRepository repository;

    public List<UnidadeMedida> findAll() {
        return repository.findAll();
    }

    @Transactional
    public UnidadeMedida save(UnidadeMedida unidade) {
        unidade.setNome(unidade.getNome().toUpperCase()); // Padroniza para a obra
        return repository.save(unidade);
    }

    @Transactional
    public UnidadeMedida update(UUID id, String novoNome) {
        UnidadeMedida unidade = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Unidade não encontrada!"));
        unidade.setNome(novoNome.toUpperCase());
        return repository.save(unidade);
    }

    @Transactional
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}