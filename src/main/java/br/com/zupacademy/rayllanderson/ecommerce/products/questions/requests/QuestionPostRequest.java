package br.com.zupacademy.rayllanderson.ecommerce.products.questions.requests;

import br.com.zupacademy.rayllanderson.ecommerce.products.model.Product;
import br.com.zupacademy.rayllanderson.ecommerce.products.questions.model.Question;
import br.com.zupacademy.rayllanderson.ecommerce.users.model.User;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotBlank;

public class QuestionPostRequest {

    @NotBlank
    private final String title;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public QuestionPostRequest(@NotBlank String title) {
        this.title = title;
    }

    public Question toModel(User user, Product product){
        return new Question(title, user, product);
    }
}
