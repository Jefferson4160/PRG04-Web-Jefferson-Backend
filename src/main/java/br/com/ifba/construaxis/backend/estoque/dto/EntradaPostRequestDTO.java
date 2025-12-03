package br.com.ifba.construaxis.backend.estoque.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class EntradaPostRequestDTO {

    private LocalDate dataEntrada;
    private UUID itemId;
    private UUID fornecedorId;
    private UUID almoxarifeId;

    private String notaFiscalRa;
    private Double quantidadeNf;
    private Double quantidadeEntrada;
    private Double valorUnitario;
}
