package br.com.zupacademy.rayllanderson.ecommerce.users.controllers;

import br.com.zupacademy.rayllanderson.ecommerce.users.model.User;
import br.com.zupacademy.rayllanderson.ecommerce.users.requests.UserPostRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class SaveUserController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    @Transactional
    public ResponseEntity<?> save(@RequestBody @Valid UserPostRequest request){
        User userToBeSaved = request.toModel(new BCryptPasswordEncoder());
        manager.persist(userToBeSaved);
        return ResponseEntity.ok().build();
    }
}
