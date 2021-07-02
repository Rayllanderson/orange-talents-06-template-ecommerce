package br.com.zupacademy.rayllanderson.ecommerce.orders.model;

import br.com.zupacademy.rayllanderson.ecommerce.orders.enums.OrderStatus;
import br.com.zupacademy.rayllanderson.ecommerce.orders.enums.PaymentGateway;
import br.com.zupacademy.rayllanderson.ecommerce.products.model.Product;
import br.com.zupacademy.rayllanderson.ecommerce.users.model.User;
import org.springframework.web.util.UriComponentsBuilder;

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
    private Integer quantity;

    @NotNull
    @Column(nullable = false)
    private Double price;

    @NotNull
    @ManyToOne(cascade = CascadeType.MERGE)
    private Product selectedProduct;

    @NotNull
    @ManyToOne
    private User buyer;

    @Enumerated
    private PaymentGateway paymentGateway;

    @Enumerated
    private OrderStatus status = OrderStatus.STARTED;

    @Deprecated
    private Order(){}

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

    public Long getSellerId() {
        return this.getSeller().getId();
    }

    public String getReturnUrl(UriComponentsBuilder uriComponentsBuilder) {
        return this.paymentGateway.getReturnUrl(this,uriComponentsBuilder);
    }

    public User getSeller(){
        return this.selectedProduct.getOwner();
    }

    public User getBuyer(){
        return this.buyer;
    }

    public String getProductName(){
        return this.selectedProduct.getName();
    }

    public void finish(){
        this.status = OrderStatus.FINISHED;
    }

    public boolean hasSuccessfullyProcessed(){
        return this.status.equals(OrderStatus.FINISHED);
    }
}
