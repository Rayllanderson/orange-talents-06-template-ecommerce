package br.com.zupacademy.rayllanderson.ecommerce.categories.controllers;

import br.com.zupacademy.rayllanderson.ecommerce.categories.model.Category;
import br.com.zupacademy.rayllanderson.ecommerce.categories.requests.CategoryPostRequest;
import br.com.zupacademy.rayllanderson.ecommerce.categories.validator.MotherCategoryExistsValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping("/categories")
public class SaveCategoryController {

    @PersistenceContext
    private EntityManager manager;
    private final MotherCategoryExistsValidator motherCategoryValidator;

    public SaveCategoryController(EntityManager manager, MotherCategoryExistsValidator motherCategoryValidator) {
        this.manager = manager;
        this.motherCategoryValidator = motherCategoryValidator;
    }

    @InitBinder
    public void init(WebDataBinder binder){
        binder.addValidators(motherCategoryValidator);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> save(@RequestBody @Valid CategoryPostRequest request){
        Category categoryToBeSaved = request.toModel(manager);
        manager.persist(categoryToBeSaved);
        return ResponseEntity.ok().build();
    }
}
