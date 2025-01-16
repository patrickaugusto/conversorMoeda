package br.com.conversor.demo.dto;

public record ConversaoResponse(
        Double valorConvertido,
        String ultimaAtualizacao
) {
}
