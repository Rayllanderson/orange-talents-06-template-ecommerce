package br.com.zupacademy.rayllanderson.ecommerce.products.questions.controllers;

import br.com.zupacademy.rayllanderson.ecommerce.products.model.Product;
import br.com.zupacademy.rayllanderson.ecommerce.products.questions.model.Question;
import br.com.zupacademy.rayllanderson.ecommerce.products.questions.requests.QuestionPostRequest;
import br.com.zupacademy.rayllanderson.ecommerce.products.questions.services.EmailSender;
import br.com.zupacademy.rayllanderson.ecommerce.users.model.User;
import org.springframework.beans.factory.annotation.Autowired;
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
public class SendQuestionController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private EmailSender emailSender;

    @Transactional
    @PostMapping("/products/{id}/questions")
    public ResponseEntity<?> sendQuestion(@PathVariable Long id, @Valid @RequestBody QuestionPostRequest request,
                                          @AuthenticationPrincipal User authUser){

        Product selectedProduct = manager.find(Product.class, id);
        if(selectedProduct == null) return ResponseEntity.notFound().build();

        Question question = request.toModel(authUser, selectedProduct);
        manager.persist(question);

        emailSender.sendQuestionEmail(question);

        return ResponseEntity.ok().build();
    }
}
