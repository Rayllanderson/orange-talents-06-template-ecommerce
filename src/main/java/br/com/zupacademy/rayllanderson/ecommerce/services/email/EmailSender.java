package br.com.zupacademy.rayllanderson.ecommerce.services.email;

import br.com.zupacademy.rayllanderson.ecommerce.orders.model.Order;
import br.com.zupacademy.rayllanderson.ecommerce.products.questions.model.Question;

public interface EmailSender {

    void sendQuestionEmail(Question question);
    void sendOrderEmail(Order order);
}
