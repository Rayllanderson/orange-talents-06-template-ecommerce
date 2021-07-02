package br.com.zupacademy.rayllanderson.ecommerce.external.systems.requests;

import javax.validation.constraints.NotNull;

public class NotaFiscalRequest {

    @NotNull
    private final Long orderId;
    @NotNull
    private final Long buyerId;

    public NotaFiscalRequest(Long orderId, Long buyerId) {
        this.orderId = orderId;
        this.buyerId = buyerId;
    }

    @Override
    public String toString() {
        return "NotaFiscalRequest{" +
                "orderId=" + orderId +
                ", buyerId=" + buyerId +
                '}';
    }
}
