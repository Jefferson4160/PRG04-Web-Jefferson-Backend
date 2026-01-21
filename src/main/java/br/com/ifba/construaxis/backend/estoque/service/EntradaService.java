package br.com.ifba.construaxis.backend.estoque.service;

import br.com.ifba.construaxis.backend.estoque.dto.EntradaGetResponseDTO;
import br.com.ifba.construaxis.backend.estoque.dto.EntradaPostRequestDTO;
import br.com.ifba.construaxis.backend.estoque.entity.Entrada;
import br.com.ifba.construaxis.backend.estoque.entity.Item;
import br.com.ifba.construaxis.backend.estoque.mapper.EntradaMapper;
import br.com.ifba.construaxis.backend.estoque.repository.EntradaRepository;
import br.com.ifba.construaxis.backend.estoque.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EntradaService {

    @Autowired
    private EntradaRepository entradaRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private EntradaMapper entradaMapper; // Injetando o tradutor

    @Transactional
    public EntradaGetResponseDTO registrarEntrada(EntradaPostRequestDTO dto) {
        // 1. Busca o item no banco
        Item item = itemRepository.findById(dto.getItemId())
                .orElseThrow(() -> new RuntimeException("Item não encontrado!"));

        // 2. Usa o Mapper para transformar DTO em Entidade
        Entrada entrada = entradaMapper.toEntity(dto, item);

        // 3. Salva no banco
        Entrada entradaSalva = entradaRepository.save(entrada);

        // 4. Retorna o DTO de resposta para o Frontend
        return entradaMapper.toGetResponseDTO(entradaSalva);
    }

    public List<EntradaGetResponseDTO> findAll() {
        List<Entrada> entradas = entradaRepository.findAll();
        return entradaMapper.toGetResponseDTOList(entradas);
    }
}