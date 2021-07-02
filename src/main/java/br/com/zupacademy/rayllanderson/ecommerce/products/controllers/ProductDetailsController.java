package br.com.zupacademy.rayllanderson.ecommerce.products.controllers;

import br.com.zupacademy.rayllanderson.ecommerce.products.model.Product;
import br.com.zupacademy.rayllanderson.ecommerce.products.responses.ProductResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RestController
@RequestMapping("/products")
public class ProductDetailsController {

    @PersistenceContext
    private EntityManager manager;

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findByID(@PathVariable Long id){
        Product product = manager.find(Product.class, id);
        if(product == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new ProductResponse(product));
    }

}
