package br.com.zupacademy.rayllanderson.ecommerce.transactions.tasks;

import br.com.zupacademy.rayllanderson.ecommerce.orders.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class Ranking implements TaskProcessor {

    private final RestTemplate restTemplate;

    @Autowired
    public Ranking(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void process(Order order) {
        Long sellerId = order.getSellerId();
        Long orderId = order.getId();
        Map<String, Long> body = Map.of("orderId", orderId, "sellerId", sellerId);
        String url = "http://localhost:8080/public/rankings";
        restTemplate.postForEntity(url, body, String.class);
    }
}
