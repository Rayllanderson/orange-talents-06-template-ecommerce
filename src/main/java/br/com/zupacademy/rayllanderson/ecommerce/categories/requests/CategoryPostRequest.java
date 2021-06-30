package br.com.zupacademy.rayllanderson.ecommerce.categories.requests;

import br.com.zupacademy.rayllanderson.ecommerce.categories.model.Category;
import br.com.zupacademy.rayllanderson.ecommerce.core.validation.annotations.UniqueValue;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CategoryPostRequest {

    @NotBlank @UniqueValue(domainClass = Category.class, field = "name")
    private final String name;
    private final Long motherCategoryId;

    public CategoryPostRequest(String name, Long motherId) {
        this.name = name;
        this.motherCategoryId = motherId;
    }

    public Category toModel(EntityManager manager){
        Category category = new Category(name);
        if (motherCategoryId != null){
            @NotNull Category motherCategory = manager.find(Category.class, motherCategoryId);
            category.setMotherCategory(motherCategory);
        }
        return category;
    }

    public Long getMotherCategoryId() {
        return motherCategoryId;
    }
}
