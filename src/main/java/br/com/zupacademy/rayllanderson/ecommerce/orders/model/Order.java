package br.com.zupacademy.rayllanderson.ecommerce.orders.model;

import br.com.zupacademy.rayllanderson.ecommerce.orders.enums.OrderStatus;
import br.com.zupacademy.rayllanderson.ecommerce.orders.enums.PaymentGateway;
import br.com.zupacademy.rayllanderson.ecommerce.products.model.Product;
import br.com.zupacademy.rayllanderson.ecommerce.users.model.User;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull @Positive @Min(1)
    @Column(nullable = false)
    private final Integer quantity;

    @NotNull
    @Column(nullable = false)
    private final Double price;

    @NotNull
    @ManyToOne(cascade = CascadeType.MERGE)
    private final Product selectedProduct;

    @NotNull
    @ManyToOne
    private final User buyer;

    @Enumerated
    private final PaymentGateway paymentGateway;

    @Enumerated
    private OrderStatus status = OrderStatus.STARTED;

    public Order(Integer quantity, Product selectedProduct, User buyer, PaymentGateway paymentGateway) {
        this.quantity = quantity;
        this.price = selectedProduct.getPrice();
        this.selectedProduct = selectedProduct;
        this.buyer = buyer;
        this.paymentGateway = paymentGateway;
    }

    public Long getId() {
        return id;
    }

    public Long getBuyerId() {
        return buyer.getId();
    }

    public String getReturnUrl() {
        return this.paymentGateway.getReturnUrl(this);
    }

    public User getSeller(){
        return this.selectedProduct.getOwner();
    }

    public String getProductName(){
        return this.selectedProduct.getName();
    }
}
