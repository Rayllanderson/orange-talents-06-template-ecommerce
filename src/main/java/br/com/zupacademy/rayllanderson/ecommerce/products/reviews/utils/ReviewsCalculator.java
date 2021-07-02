package br.com.zupacademy.rayllanderson.ecommerce.products.reviews.utils;

import br.com.zupacademy.rayllanderson.ecommerce.products.reviews.model.Review;

import java.util.Set;
import java.util.stream.Collectors;

public class ReviewsCalculator {

    private final Set<Review> reviews;

    public ReviewsCalculator(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public double average(){
        Set<Integer> ratings = ratingToSet();
        return ratings.stream().mapToDouble(rating -> rating).average().orElse(0.0);
    }

    public int total(){
        return this.reviews.size();
    }

    private Set<Integer> ratingToSet(){
        return reviews.stream().map(Review::getRating).collect(Collectors.toSet());
    }
}
