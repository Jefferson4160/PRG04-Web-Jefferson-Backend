package br.com.ifba.construaxis.backend.estoque.mapper;

import br.com.ifba.construaxis.backend.estoque.dto.EntradaGetResponseDTO;
import br.com.ifba.construaxis.backend.estoque.dto.EntradaPostRequestDTO;
import br.com.ifba.construaxis.backend.estoque.entity.Entrada;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EntradaMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "valorTotal", ignore = true)
    Entrada toEntity(EntradaPostRequestDTO dto);

    @Mapping(target = "entradaId", source = "id")
    EntradaGetResponseDTO toDTO(Entrada entrada);
}
