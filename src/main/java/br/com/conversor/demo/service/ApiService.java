package br.com.conversor.demo.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiService {

    @Autowired
    private ApiRequisicao apiRequisicao;

    public JsonObject obterTaxasDeCambio() throws Exception {
        String respostaJson = apiRequisicao.obterTaxas();
        JsonObject jsonObject = JsonParser.parseString(respostaJson).getAsJsonObject();

        JsonObject taxasDeCambio = jsonObject.getAsJsonObject("conversion_rates");

        String ultimaAtualizacao = jsonObject.get("time_last_update_utc").getAsString();

        taxasDeCambio.addProperty("last_update", ultimaAtualizacao);

        return taxasDeCambio;
    }
}
