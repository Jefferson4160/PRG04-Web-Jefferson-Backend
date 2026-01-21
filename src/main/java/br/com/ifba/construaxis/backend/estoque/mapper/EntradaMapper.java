package br.com.ifba.construaxis.backend.estoque.mapper;

import br.com.ifba.construaxis.backend.estoque.dto.EntradaGetResponseDTO;
import br.com.ifba.construaxis.backend.estoque.dto.EntradaPostRequestDTO;
import br.com.ifba.construaxis.backend.estoque.entity.Entrada;
import br.com.ifba.construaxis.backend.estoque.entity.Item;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EntradaMapper {

    /**
     * Converte o DTO que vem do React para a Entidade que vai para o Banco.
     */
    public Entrada toEntity(EntradaPostRequestDTO dto, Item item) {
        if (dto == null) return null;

        Entrada entrada = new Entrada();
        entrada.setItem(item);
        entrada.setQuantidade(dto.getQuantidade());
        entrada.setNumeroNotaFiscal(dto.getNumeroNotaFiscal());
        
        return entrada;
    }

    /**
     * Converte a Entidade do Banco para o DTO que o React vai exibir na tabela.
     */
    public EntradaGetResponseDTO toGetResponseDTO(Entrada entrada) {
        if (entrada == null) return null;

        EntradaGetResponseDTO dto = new EntradaGetResponseDTO();
        dto.setItemDescricao(entrada.getItem().getDescricao());
        dto.setQuantidade(entrada.getQuantidade());
        dto.setData(entrada.getDataEntrada());
        dto.setNf(entrada.getNumeroNotaFiscal());

        return dto;
    }

    /**
     * Converte uma lista inteira de Entradas para uma lista de DTOs.
     */
    public List<EntradaGetResponseDTO> toGetResponseDTOList(List<Entrada> entradas) {
        return entradas.stream()
                .map(this::toGetResponseDTO)
                .collect(Collectors.toList());
    }
}