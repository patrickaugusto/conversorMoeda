package br.com.conversor.demo.service;

import br.com.conversor.demo.dto.ConversaoRequest;
import br.com.conversor.demo.dto.ConversaoResponse;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@Service
public class ConversaoService {

    @Autowired
    private ApiService apiServico;

    public void setApiService(ApiService apiServico) {
        this.apiServico = apiServico;
    }

    public ConversaoResponse converter(ConversaoRequest dtoRequest) throws Exception {
        JsonObject taxasDeCambio = apiServico.obterTaxasDeCambio();
        Double taxa = taxasDeCambio.get(dtoRequest.moedaDestino()).getAsDouble();
        Double valorConvertido = dtoRequest.valor() * taxa;

        String ultimaAtualizacao = taxasDeCambio.get("last_update").getAsString();

        return new ConversaoResponse(valorConvertido, ultimaAtualizacao);
    }

}

