package br.com.zupacademy.rayllanderson.ecommerce.transactions.tasks;

import br.com.zupacademy.rayllanderson.ecommerce.orders.model.Order;
import org.springframework.stereotype.Component;

@Component
public interface TaskProcessor {

    void process(Order order);
}
