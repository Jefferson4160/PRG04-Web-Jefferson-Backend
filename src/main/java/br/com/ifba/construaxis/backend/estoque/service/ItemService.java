package br.com.ifba.construaxis.backend.estoque.service;

import br.com.ifba.construaxis.backend.estoque.dto.ItemPostRequestDTO;
import br.com.ifba.construaxis.backend.estoque.entity.Categoria;
import br.com.ifba.construaxis.backend.estoque.entity.Item;
import br.com.ifba.construaxis.backend.estoque.entity.UnidadeMedida;
import br.com.ifba.construaxis.backend.estoque.repository.CategoriaRepository;
import br.com.ifba.construaxis.backend.estoque.repository.ItemRepository;
import br.com.ifba.construaxis.backend.estoque.repository.UnidadeMedidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private UnidadeMedidaRepository unidadeRepository;

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    @Transactional
    public Item save(ItemPostRequestDTO dto) {
        // 1. Busca os objetos completos
        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada!"));

        UnidadeMedida unidade = unidadeRepository.findById(dto.getUnidadeMedidaId())
                .orElseThrow(() -> new RuntimeException("Unidade de medida não encontrada!"));

        // 2. Monta o objeto Item (Corrigido o nome dos métodos)
        Item item = new Item();
        item.setDescricao(dto.getDescricao());
        item.setCategoria(categoria); // ANTES ESTAVA setCategoriaId (ERRADO)
        item.setUnidadeMedida(unidade);

        // 3. Salva no banco
        return itemRepository.save(item);
    }
}