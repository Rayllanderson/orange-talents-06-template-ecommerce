package br.com.zupacademy.rayllanderson.ecommerce.external.systems.requests;

import javax.validation.constraints.NotNull;

public class RankingRequest {

    @NotNull
    private final Long sellerId;
    @NotNull
    private final Long orderId;

    public RankingRequest(Long sellerId, Long orderId) {
        this.sellerId = sellerId;
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "RankingRequest{" +
                "sellerId=" + sellerId +
                ", orderId=" + orderId +
                '}';
    }
}
