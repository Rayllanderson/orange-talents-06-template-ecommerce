package br.com.zupacademy.rayllanderson.ecommerce.external.systems.controllers;

import br.com.zupacademy.rayllanderson.ecommerce.external.systems.requests.NotaFiscalRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/public/notas-ficais")
public class BrazilianNotaFiscalCreatorController {

    @PostMapping
    public void create(@Valid @RequestBody NotaFiscalRequest request){
        System.out.println(request);
    }
}
