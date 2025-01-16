package br.com.conversor.demo.controller;

import br.com.conversor.demo.dto.ConversaoResponse;
import br.com.conversor.demo.service.ConversaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/conversor")
public class ConversorController {

    @Autowired
    private ConversaoService conversaoService;

    @GetMapping("/converter")
    public ResponseEntity<ConversaoResponse> converter(@RequestParam Double valor,
                                                       @RequestParam String moedaOrigem,
                                                       @RequestParam String moedaDestino) throws Exception {
        var dtoResponse = conversaoService.converter(valor, moedaOrigem, moedaDestino);
        return ResponseEntity.ok().body(dtoResponse);
    }
}
