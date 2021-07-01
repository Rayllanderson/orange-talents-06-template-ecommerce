package br.com.zupacademy.rayllanderson.ecommerce.products.characters.model;

import br.com.zupacademy.rayllanderson.ecommerce.products.model.Product;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Character {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotNull
    @ManyToOne(optional = false)
    private Product product;

    @Deprecated
    private Character(){}

    public Character(String name, String description, Product product) {
        this.name = name;
        this.description = description;
        this.product = product;
    }
}
