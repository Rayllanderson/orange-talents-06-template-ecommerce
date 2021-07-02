package br.com.zupacademy.rayllanderson.ecommerce.external.systems.controllers;

import br.com.zupacademy.rayllanderson.ecommerce.external.systems.requests.RankingRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/public/rankings")
public class RankingCreatorController {

    @PostMapping
    public void create(@Valid @RequestBody RankingRequest request){
        System.out.println(request);
    }
}
