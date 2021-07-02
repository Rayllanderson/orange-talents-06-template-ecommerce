package br.com.zupacademy.rayllanderson.ecommerce.products.characters.responses;

import br.com.zupacademy.rayllanderson.ecommerce.products.characters.model.Character;

public class CharacterResponse {

    private final String name;
    private final String description;

    public CharacterResponse(Character character) {
        this.name = character.getName();
        this.description = character.getDescription();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
