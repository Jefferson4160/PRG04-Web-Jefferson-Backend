package br.com.ifba.construaxis.backend.cnpj.dto;

import lombok.Data;

@Data
public class CnpjResponseDTO {
    private String nome;
    private String fantasia;
    private String situacao;
    private String logradouro;
    private String numero;
    private String bairro;
    private String municipio;
    private String uf;
    private String cep;
}
