package br.com.zupacademy.rayllanderson.ecommerce.products.questions.model;

import br.com.zupacademy.rayllanderson.ecommerce.products.model.Product;
import br.com.zupacademy.rayllanderson.ecommerce.users.model.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Question {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String title;

    @NotNull
    @ManyToOne
    private User userWhoAsked;

    @NotNull
    @ManyToOne
    private Product product;

    @NotNull
    private LocalDateTime createdIn = LocalDateTime.now();

    @Deprecated
    private Question(){}

    public Question(@NotBlank String title, @NotNull User user, @NotNull Product product) {
        this.title = title;
        this.userWhoAsked = user;
        this.product = product;
    }

    public String getTitle() {
        return title;
    }

    public User getUserWhoAsked() {
        return userWhoAsked;
    }

    public Product getProduct() {
        return product;
    }
}
