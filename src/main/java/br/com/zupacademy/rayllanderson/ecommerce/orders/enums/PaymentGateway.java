package br.com.zupacademy.rayllanderson.ecommerce.orders.enums;

import br.com.zupacademy.rayllanderson.ecommerce.orders.model.Order;

public enum PaymentGateway {
    PAYPAL{
        @Override
        public String getReturnUrl(Order order){
            Long buyerId = order.getBuyerId();
            Long orderId = order.getId();
            return "paypal.com?buyerId=" + buyerId + "&redirectUrl=" + "https://paypal/api/ray-api/orders/" + orderId;
        }
    },
    PAG_SEGURO{
        @Override
        public String getReturnUrl(Order order){
            Long buyerId = order.getBuyerId();
            Long orderId = order.getId();
            return "pagseguro.com?buyerId=" + buyerId + "&redirectUrl=" + "https://pagseguro/api/ray-api/orders/" + orderId;
        }
    };

    public abstract String getReturnUrl(Order order);
}
