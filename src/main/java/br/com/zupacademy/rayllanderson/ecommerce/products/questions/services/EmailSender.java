package br.com.zupacademy.rayllanderson.ecommerce.products.questions.services;

import br.com.zupacademy.rayllanderson.ecommerce.products.questions.model.Question;

public interface EmailSender {

    void sendQuestionEmail(Question question);
}
