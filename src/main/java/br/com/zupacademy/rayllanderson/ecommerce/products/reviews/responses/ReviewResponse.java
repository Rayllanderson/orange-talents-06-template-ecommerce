package br.com.zupacademy.rayllanderson.ecommerce.products.reviews.responses;

import br.com.zupacademy.rayllanderson.ecommerce.products.reviews.model.Review;

public class ReviewResponse {

    private final String title;
    private final Integer rating;
    private final String description;

    public ReviewResponse(Review review) {
        this.title = review.getTitle();
        this.rating = review.getRating();
        this.description = review.getDescription();
    }

    public String getTitle() {
        return title;
    }

    public Integer getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }
}
