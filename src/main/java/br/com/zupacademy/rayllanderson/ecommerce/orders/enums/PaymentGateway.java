package br.com.zupacademy.rayllanderson.ecommerce.orders.enums;

import br.com.zupacademy.rayllanderson.ecommerce.orders.model.Order;
import org.springframework.web.util.UriComponentsBuilder;

public enum PaymentGateway {
    PAYPAL{
        @Override
        public String getReturnUrl(Order order, UriComponentsBuilder uriComponentsBuilder){
            Long buyerId = order.getBuyerId();
            Long orderId = order.getId();
            String redirectUrl = uriComponentsBuilder.path("/paypal-orders/{id}").buildAndExpand(orderId).toString();
            return "paypal.com?buyerId=" + buyerId + "&redirectUrl=" + redirectUrl;
        }
    },
    PAG_SEGURO{
        @Override
        public String getReturnUrl(Order order, UriComponentsBuilder uriComponentsBuilder){
            Long buyerId = order.getBuyerId();
            Long orderId = order.getId();
            String redirectUrl = uriComponentsBuilder.path("/pagseguro-orders/{id}").buildAndExpand(orderId).toString();
            return "pagseguro.com?buyerId=" + buyerId + "&redirectUrl=" + redirectUrl;
        }
    };

    public abstract String getReturnUrl(Order order, UriComponentsBuilder uriComponentsBuilder);
}
