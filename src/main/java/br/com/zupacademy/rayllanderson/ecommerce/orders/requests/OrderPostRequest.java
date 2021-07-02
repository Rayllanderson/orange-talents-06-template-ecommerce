package br.com.zupacademy.rayllanderson.ecommerce.orders.requests;

import br.com.zupacademy.rayllanderson.ecommerce.orders.enums.PaymentGateway;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class OrderPostRequest {

    @NotNull
    private final Long productId;
    @NotNull @Positive @Min(1)
    private final Integer quantity;
    @NotNull
    private final PaymentGateway gateway;

    public OrderPostRequest(Long productId, Integer quantity, PaymentGateway gateway) {
        this.productId = productId;
        this.quantity = quantity;
        this.gateway = gateway;
    }

    public Long getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public PaymentGateway getGateway() {
        return gateway;
    }
}
