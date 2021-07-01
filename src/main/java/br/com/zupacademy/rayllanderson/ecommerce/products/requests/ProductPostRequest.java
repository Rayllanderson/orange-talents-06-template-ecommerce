package br.com.zupacademy.rayllanderson.ecommerce.products.requests;

import br.com.zupacademy.rayllanderson.ecommerce.categories.model.Category;
import br.com.zupacademy.rayllanderson.ecommerce.core.validation.annotations.Exists;
import br.com.zupacademy.rayllanderson.ecommerce.products.characters.requests.CharacterRequest;
import br.com.zupacademy.rayllanderson.ecommerce.products.model.Product;
import br.com.zupacademy.rayllanderson.ecommerce.users.model.User;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

public class ProductPostRequest {

    @NotBlank
    private String name;

    @NotNull @DecimalMin("0.1")
    private Double price;

    @NotNull @PositiveOrZero
    private Integer quantity;

    @NotBlank @Size(max = 1000)
    private String description;

    @NotNull @Exists(domainClass = Category.class, field = "id")
    private Long categoryId;

    @Size(min = 3) @Valid
    private Set<CharacterRequest> characters = new HashSet<>();

    public ProductPostRequest(String name, Double price, Integer quantity, String description, Long categoryId,
                              Set<CharacterRequest> characters) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.categoryId = categoryId;
        this.characters.addAll(characters);
    }

    public Product toModel(User owner, EntityManager manager){
        @NotNull Category category = manager.find(Category.class, categoryId);
        return new Product(name, price, quantity, description, characters, owner, category);
    }
}
