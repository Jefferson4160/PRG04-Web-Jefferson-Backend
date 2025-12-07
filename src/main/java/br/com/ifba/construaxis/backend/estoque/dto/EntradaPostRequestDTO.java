package br.com.ifba.construaxis.backend.estoque.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class EntradaPostRequestDTO {

    @NotNull(message = "A data de entrada é obrigatória.")
    private LocalDate dataEntrada;

    @NotNull(message = "O item é obrigatório.")
    private UUID itemId;

    @NotNull(message = "O fornecedor é obrigatório.")
    private UUID fornecedorId;

    @NotNull(message = "O almoxarife é obrigatório.")
    private UUID almoxarifeId;

    @NotBlank(message = "A nota fiscal é obrigatória.")
    private String notaFiscalRa;

    @NotNull(message = "A quantidade da NF é obrigatória.")
    @Positive(message = "A quantidade da NF deve ser maior que zero.")
    private Double quantidadeNf;

    @NotNull(message = "A quantidade de entrada é obrigatória.")
    @Positive(message = "A quantidade de entrada deve ser maior que zero.")
    private Double quantidadeEntrada;

    @NotNull(message = "O valor unitário é obrigatório.")
    @Positive(message = "O valor unitário deve ser maior que zero.")
    private Double valorUnitario;
}
