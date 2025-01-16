package br.com.conversor.demo.service;

import br.com.conversor.demo.dto.ConversaoRequest;
import br.com.conversor.demo.dto.ConversaoResponse;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

@Service
public class ConversaoService {

    @Autowired
    private ApiService apiServico;

    public ConversaoResponse converter(ConversaoRequest dtoRequest) throws Exception {
        JsonObject taxasDeCambio = apiServico.obterTaxasDeCambio(dtoRequest.moedaOrigem());
        Double taxa = taxasDeCambio.get(dtoRequest.moedaDestino()).getAsDouble();
        Double valorConvertido = dtoRequest.valor() * taxa;

        String ultimaAtualizacao = taxasDeCambio.get("last_update").getAsString();

        String ultimaAtualizacaoFormatada = formatarData(ultimaAtualizacao);

        return new ConversaoResponse(valorConvertido, ultimaAtualizacaoFormatada);
    }

    private String formatarData(String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss", Locale.ENGLISH);

        try {
            LocalDateTime dataAtualizacao = LocalDateTime.parse(data, formatter);
            return dataAtualizacao.format(outputFormatter);
        } catch (DateTimeParseException e) {
            return "Data inválida";
        }
    }

}

