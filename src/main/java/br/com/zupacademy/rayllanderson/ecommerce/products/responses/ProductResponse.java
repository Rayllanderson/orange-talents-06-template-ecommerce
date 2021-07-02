package br.com.zupacademy.rayllanderson.ecommerce.products.responses;

import br.com.zupacademy.rayllanderson.ecommerce.products.characters.responses.CharacterResponse;
import br.com.zupacademy.rayllanderson.ecommerce.products.images.model.Image;
import br.com.zupacademy.rayllanderson.ecommerce.products.model.Product;
import br.com.zupacademy.rayllanderson.ecommerce.products.questions.model.Question;
import br.com.zupacademy.rayllanderson.ecommerce.products.reviews.responses.ReviewResponse;
import br.com.zupacademy.rayllanderson.ecommerce.products.reviews.utils.ReviewsCalculator;

import java.util.Set;

public class ProductResponse {

    private final String name;
    private final Set<String> imagesUrl;
    private final Double price;
    private final String description;
    private final Double averageRating;
    private final Integer totalRatingNumber;
    private final String category;
    private final Set<CharacterResponse> characters;
    private final Set<ReviewResponse> reviews;
    private final Set<String> questions;

    public ProductResponse(Product product) {
        this.name = product.getName();
        this.price = product.getPrice();
        this.description = product.getDescription();
        this.category = product.getCategoryName();
        this.imagesUrl = product.mapImages(Image::getUrl);
        this.characters = product.mapCharacters(CharacterResponse::new);
        this.questions = product.mapQuestions(Question::getTitle);
        this.reviews = product.mapReviews(ReviewResponse::new);

        ReviewsCalculator reviewsCalculator = product.getReviewsCalculator();
        this.averageRating = reviewsCalculator.average();
        this.totalRatingNumber = reviewsCalculator.total();
    }

    public String getName() {
        return name;
    }

    public Set<String> getImagesUrl() {
        return imagesUrl;
    }

    public Double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public Integer getTotalRatingNumber() {
        return totalRatingNumber;
    }

    public String getCategory() {
        return category;
    }

    public Set<CharacterResponse> getCharacters() {
        return characters;
    }

    public Set<ReviewResponse> getReviews() {
        return reviews;
    }

    public Set<String> getQuestions() {
        return questions;
    }
}
