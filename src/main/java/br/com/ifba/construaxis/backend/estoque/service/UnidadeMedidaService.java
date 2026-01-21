package br.com.ifba.construaxis.backend.estoque.service;

import br.com.ifba.construaxis.backend.estoque.entity.UnidadeMedida;
import br.com.ifba.construaxis.backend.estoque.repository.UnidadeMedidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UnidadeMedidaService {

    @Autowired
    private UnidadeMedidaRepository unidadeRepository;

    public List<UnidadeMedida> findAll() {
        return unidadeRepository.findAll();
    }
}