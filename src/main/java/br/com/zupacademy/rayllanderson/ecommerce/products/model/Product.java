package br.com.zupacademy.rayllanderson.ecommerce.products.model;

import br.com.zupacademy.rayllanderson.ecommerce.categories.model.Category;
import br.com.zupacademy.rayllanderson.ecommerce.products.characters.model.Character;
import br.com.zupacademy.rayllanderson.ecommerce.products.characters.requests.CharacterRequest;
import br.com.zupacademy.rayllanderson.ecommerce.products.images.model.Image;
import br.com.zupacademy.rayllanderson.ecommerce.users.model.User;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotNull @DecimalMin("0.1")
    @Column(nullable = false)
    private Double price;

    @NotNull @PositiveOrZero
    @Column(nullable = false)
    private Integer quantity;

    @NotBlank @Size(max = 1000)
    @Column(nullable = false, length = 1000)
    private String description;

    @Size(min = 3)
    @OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST)
    private final Set<Character> characters = new HashSet<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.MERGE)
    private final Set<Image> images = new HashSet<>();

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
    public boolean belongToThatUser(User user){
        return this.owner.equals(user);
    }

    /**
     * Adiciona as imagens e associa as imagens com o produto.
     */
    public void addAndAssignImages(Set<String> links) {
        this.images.addAll(links.stream().map(link -> new Image(link, this)).collect(Collectors.toSet()));
    }
}
