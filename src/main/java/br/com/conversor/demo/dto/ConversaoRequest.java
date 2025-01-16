package br.com.conversor.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ConversaoRequest(
        @NotNull
        Double valor,
        @NotBlank
        String moedaOrigem,
        @NotBlank
        String moedaDestino
) {
}
