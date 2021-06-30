package br.com.zupacademy.rayllanderson.ecommerce.categories.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Category {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @ManyToOne
    private Category motherCategory;

    @Deprecated
    private Category(){}

    public Category(String name) {
        this.name = name;
    }

    /**
     * Usado para setar a categoria mãe, se existir. Se não tiver categoria mãe, não precisa usar.
     */
    public void setMotherCategory(Category motherCategory) {
        this.motherCategory = motherCategory;
    }
}
