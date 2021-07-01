package br.com.zupacademy.rayllanderson.ecommerce.products.reviews.controllers;

import br.com.zupacademy.rayllanderson.ecommerce.products.model.Product;
import br.com.zupacademy.rayllanderson.ecommerce.products.reviews.model.Review;
import br.com.zupacademy.rayllanderson.ecommerce.products.reviews.requests.ReviewPostRequest;
import br.com.zupacademy.rayllanderson.ecommerce.users.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
public class AddReviewController {

    @PersistenceContext
    private EntityManager manager;

    @Transactional
    @PostMapping("/products/{productId}/reviews")
    public ResponseEntity<?> addReview(@PathVariable Long productId, @RequestBody @Valid ReviewPostRequest request,
                                       @AuthenticationPrincipal User authUser){

        Product selectedProduct = manager.find(Product.class, productId);
        if (selectedProduct == null) return ResponseEntity.notFound().build();

        Review review = request.toModel(authUser, selectedProduct);
        selectedProduct.addReview(review);

        manager.merge(selectedProduct);
        return ResponseEntity.ok().build();
    }
}
