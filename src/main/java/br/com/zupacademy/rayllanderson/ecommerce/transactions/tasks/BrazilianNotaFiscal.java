package br.com.zupacademy.rayllanderson.ecommerce.transactions.tasks;

import br.com.zupacademy.rayllanderson.ecommerce.orders.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class BrazilianNotaFiscal implements TaskProcessor {

    private final RestTemplate restTemplate;

    @Autowired
    public BrazilianNotaFiscal(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void process(Order order) {
        long orderId = order.getId();
        long buyerId = order.getBuyerId();
        Map<String, Long> body = Map.of("orderId", orderId, "buyerId", buyerId);
        String url = "http://localhost:8080/public/notas-ficais";
        restTemplate.postForEntity(url, body, String.class);
    }
}
