package br.com.ifba.construaxis.backend.usuario.dto;

import lombok.Data;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class UsuarioPostRequestDTO {
    // Dados do Usuário (Acesso)
    private String login;
    private String senha;
    
    // Dados da Pessoa (Identidade)
    private String nome;
    @JsonProperty("cpf") //para corrigir comunicação com o front
    private String documento;

    // Vínculo
    private UUID perfilId;
}