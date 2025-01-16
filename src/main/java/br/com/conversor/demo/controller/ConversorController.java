package br.com.conversor.demo.controller;

import br.com.conversor.demo.dto.ConversaoRequest;
import br.com.conversor.demo.dto.ConversaoResponse;
import br.com.conversor.demo.service.ConversaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/conversor")
public class ConversorController {

    @Autowired
    private ConversaoService conversaoService;

    @PostMapping("/converter")
    public ResponseEntity<ConversaoResponse> converter(@RequestBody @Valid ConversaoRequest dtoRequest) throws Exception {
        var dtoResponse = conversaoService.converter(dtoRequest);
        return ResponseEntity.ok().body(dtoResponse);
    }
}
