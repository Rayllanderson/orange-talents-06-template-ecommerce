package br.com.zupacademy.rayllanderson.ecommerce.products.images.model;

import br.com.zupacademy.rayllanderson.ecommerce.products.model.Product;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Image {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String url;

    @NotNull
    @ManyToOne(optional = false)
    private Product product;

    @Deprecated
    private Image() {
    }

    public Image(@NotNull @URL String url, @NotNull Product product) {
        this.url = url;
        this.product = product;
    }

    public String getUrl() {
        return url;
    }
}
