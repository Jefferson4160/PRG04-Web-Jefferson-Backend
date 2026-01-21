package br.com.ifba.construaxis.backend.estoque.service;

import br.com.ifba.construaxis.backend.estoque.dto.ItemPostRequestDTO;
import br.com.ifba.construaxis.backend.estoque.entity.Categoria;
import br.com.ifba.construaxis.backend.estoque.entity.Item;
import br.com.ifba.construaxis.backend.estoque.entity.UnidadeMedida;
import br.com.ifba.construaxis.backend.estoque.repository.CategoriaRepository;
import br.com.ifba.construaxis.backend.estoque.repository.ItemRepository;
import br.com.ifba.construaxis.backend.estoque.repository.UnidadeMedidaRepository;
import br.com.ifba.construaxis.backend.usuario.entity.Usuario;
import br.com.ifba.construaxis.backend.usuario.repository.UsuarioRepository;
import jakarta.persistence.EntityManager;

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

    @Autowired
    private UsuarioRepository usuarioRepository; 

    @Autowired
    private EntityManager entityManager;

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    @Transactional
    public Item save(ItemPostRequestDTO dto) {
        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada!"));

        UnidadeMedida unidade = unidadeRepository.findById(dto.getUnidadeMedidaId())
                .orElseThrow(() -> new RuntimeException("Unidade não encontrada!"));

        Usuario criador = usuarioRepository.findById(dto.getCriadoPorId())
                .orElseThrow(() -> new RuntimeException("Usuário criador não encontrado!"));

       Item item = new Item();
        item.setDescricao(dto.getDescricao());
        item.setCategoria(categoria);
        item.setUnidadeMedida(unidade);
        item.setCriadoPor(criador); // Define o vínculo obrigatório com o usuário

        // 1. Salva o item
        item = itemRepository.save(item);
        
        // 2. Força o Hibernate a sincronizar com o banco para buscar o 'codigo'
        itemRepository.flush();
        entityManager.refresh(item);

        return itemRepository.save(item);
    }
}