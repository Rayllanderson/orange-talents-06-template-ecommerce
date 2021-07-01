package br.com.zupacademy.rayllanderson.ecommerce.products.images.controllers;

import br.com.zupacademy.rayllanderson.ecommerce.products.images.requests.ImagePostRequest;
import br.com.zupacademy.rayllanderson.ecommerce.products.images.services.Uploader;
import br.com.zupacademy.rayllanderson.ecommerce.products.model.Product;
import br.com.zupacademy.rayllanderson.ecommerce.users.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.Set;

@RestController
public class SaveImageController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private final Uploader fakeUploader;

    public SaveImageController(EntityManager manager, Uploader fakeUploader) {
        this.manager = manager;
        this.fakeUploader = fakeUploader;
    }

    @Transactional
    @PostMapping("products/{productId}/images")
    public ResponseEntity<?> save (@Valid ImagePostRequest request, @PathVariable Long productId, @AuthenticationPrincipal User authUser) {
        Product product = manager.find(Product.class, productId);
        boolean userCannotAddImage = product == null || !product.belongToThatUser(authUser);
        if(userCannotAddImage){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Set<String> generatedLinks = fakeUploader.upload(request.getFiles());
        product.addAndAssignImages(generatedLinks);
        manager.merge(product);
        return ResponseEntity.ok().build();
    }

}
