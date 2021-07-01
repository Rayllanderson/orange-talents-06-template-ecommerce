package br.com.zupacademy.rayllanderson.ecommerce.products.reviews.model;

import br.com.zupacademy.rayllanderson.ecommerce.products.model.Product;
import br.com.zupacademy.rayllanderson.ecommerce.users.model.User;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class Review {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String title;

    @NotNull @Min(1) @Max(5)
    private Integer rating;

    @NotBlank @Size(max = 500)
    @Column(length = 500)
    private String description;

    @NotNull
    @ManyToOne
    private User userWhoMadeReview;

    @NotNull
    @ManyToOne
    private Product product;

    @Deprecated
    private Review(){}

    public Review(String title, Integer rating, String description, User user, Product product) {
        this.title = title;
        this.rating = rating;
        this.description = description;
        this.userWhoMadeReview = user;
        this.product = product;
    }
}
