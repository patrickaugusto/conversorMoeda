package br.com.conversor.demo.service;

import org.springframework.stereotype.Component;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class ApiRequisicao {

    public String obterTaxas(String moedaOrigem) throws Exception {

        String urlString = "https://v6.exchangerate-api.com/v6/5f3feed4bcc18739616e67c9/latest/"+moedaOrigem;
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        try (InputStreamReader reader = new InputStreamReader(connection.getInputStream())) {
            StringBuilder result = new StringBuilder();
            int data;
            while ((data = reader.read()) != -1) {
                result.append((char) data);
            }
            return result.toString();
        }
    }
}


