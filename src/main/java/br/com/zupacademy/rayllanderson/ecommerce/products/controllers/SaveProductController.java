package br.com.zupacademy.rayllanderson.ecommerce.products.controllers;

import br.com.zupacademy.rayllanderson.ecommerce.products.model.Product;
import br.com.zupacademy.rayllanderson.ecommerce.products.requests.ProductPostRequest;
import br.com.zupacademy.rayllanderson.ecommerce.users.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping("/products")
public class SaveProductController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    @Transactional
    public ResponseEntity<?> save(@RequestBody @Valid ProductPostRequest request, @AuthenticationPrincipal User authUser){
        Product productToBeSaved = request.toModel(authUser, manager);
        manager.persist(productToBeSaved);
        return ResponseEntity.ok().build();
    }
}
