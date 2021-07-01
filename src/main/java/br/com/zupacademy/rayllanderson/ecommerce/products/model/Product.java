package br.com.zupacademy.rayllanderson.ecommerce.products.model;

import br.com.zupacademy.rayllanderson.ecommerce.categories.model.Category;
import br.com.zupacademy.rayllanderson.ecommerce.products.characters.model.Character;
import br.com.zupacademy.rayllanderson.ecommerce.products.characters.requests.CharacterRequest;
import br.com.zupacademy.rayllanderson.ecommerce.users.model.User;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Product {

    @Id
    @GeneratedValue
    private Long id;
    @NotBlank
    private String name;

    @NotNull @DecimalMin("0.1")
    private Double price;

    @NotNull @PositiveOrZero
    private Integer quantity;

    @NotBlank @Size(max = 1000)
    private String description;

    @Size(min = 3)
    @OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST)
    private final Set<Character> characters = new HashSet<>();

    @NotNull
    @ManyToOne
    private User owner;

    @NotNull
    @ManyToOne
    private Category category;

    @Deprecated
    private Product(){}

    public Product(String name, Double price, Integer quantity, String description,
                   Set<CharacterRequest> charactersRequest, User owner, Category category) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.owner = owner;
        this.category = category;
        charactersRequest.forEach(request -> this.characters.add(request.toModel(this)));
    }
}
