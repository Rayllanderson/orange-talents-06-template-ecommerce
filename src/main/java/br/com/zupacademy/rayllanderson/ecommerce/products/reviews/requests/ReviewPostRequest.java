package br.com.zupacademy.rayllanderson.ecommerce.products.reviews.requests;

import br.com.zupacademy.rayllanderson.ecommerce.products.model.Product;
import br.com.zupacademy.rayllanderson.ecommerce.products.reviews.model.Review;
import br.com.zupacademy.rayllanderson.ecommerce.users.model.User;

import javax.validation.constraints.*;

public class ReviewPostRequest {

    @NotBlank
    private final String title;

    @NotNull @Min(1) @Max(5)
    private final Integer rating;

    @NotBlank @Size(max = 500)
    private final String description;

    public ReviewPostRequest(@NotBlank String title, @Size(min = 1, max = 5) Integer rating,
                             @NotBlank @Size(max = 500) String description) {
        this.title = title;
        this.rating = rating;
        this.description = description;
    }

    public Review toModel(User user, Product product){
        return new Review(title, rating, description, user, product);
    }
}
