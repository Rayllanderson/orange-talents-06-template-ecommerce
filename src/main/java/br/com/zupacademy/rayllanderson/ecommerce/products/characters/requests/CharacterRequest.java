package br.com.zupacademy.rayllanderson.ecommerce.products.characters.requests;

import br.com.zupacademy.rayllanderson.ecommerce.products.characters.model.Character;
import br.com.zupacademy.rayllanderson.ecommerce.products.model.Product;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class CharacterRequest {

    @NotBlank
    private final String name;
    @NotBlank
    private final String description;

    public CharacterRequest(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Character toModel(Product product){
        return new Character(name, description, product);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CharacterRequest that = (CharacterRequest) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
