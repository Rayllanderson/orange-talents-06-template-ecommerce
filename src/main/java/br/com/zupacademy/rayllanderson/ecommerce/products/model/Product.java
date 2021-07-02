package br.com.zupacademy.rayllanderson.ecommerce.products.model;

import br.com.zupacademy.rayllanderson.ecommerce.categories.model.Category;
import br.com.zupacademy.rayllanderson.ecommerce.products.characters.model.Character;
import br.com.zupacademy.rayllanderson.ecommerce.products.characters.requests.CharacterRequest;
import br.com.zupacademy.rayllanderson.ecommerce.products.images.model.Image;
import br.com.zupacademy.rayllanderson.ecommerce.products.questions.model.Question;
import br.com.zupacademy.rayllanderson.ecommerce.products.reviews.model.Review;
import br.com.zupacademy.rayllanderson.ecommerce.products.reviews.utils.ReviewsCalculator;
import br.com.zupacademy.rayllanderson.ecommerce.users.model.User;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
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

    @OneToMany(mappedBy = "product", cascade = CascadeType.MERGE)
    private final Set<Review> reviews = new HashSet<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.MERGE)
    private final Set<Question> questions = new HashSet<>();

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

    public void addReview(Review review){
        this.reviews.add(review);
    }

    public User getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getDescription() {
        return description;
    }

    public String getCategoryName() {
        return category.getName();
    }

    public ReviewsCalculator getReviewsCalculator(){
        return new ReviewsCalculator(this.reviews);
    }

    public <T> Set<T> mapCharacters(Function<Character, T> mapFunction){
        return this.characters.stream().map(mapFunction).collect(Collectors.toSet());
    }

    public <T> Set<T> mapImages(Function<Image, T> mapFunction){
        return this.images.stream().map(mapFunction).collect(Collectors.toSet());
    }

    public <T> Set<T> mapQuestions(Function<Question, T> mapFunction){
        return this.questions.stream().map(mapFunction).collect(Collectors.toSet());
    }

    public <T> Set<T> mapReviews(Function<Review, T> mapFunction){
        return this.reviews.stream().map(mapFunction).collect(Collectors.toSet());
    }

    private boolean hasStockFor(int quantity){
        return this.quantity > 0 && this.quantity >= quantity;
    }

    private void reduceFromStock(int quantity){
        this.quantity -= quantity;
    }

    public boolean checkIfHasStockThenReduceFromStock(int quantity){
        if (hasStockFor(quantity)){
            reduceFromStock(quantity);
            return true;
        }
        return false;
    }

}
