package br.com.zupacademy.rayllanderson.ecommerce.products.questions.services;

import br.com.zupacademy.rayllanderson.ecommerce.products.questions.model.Question;
import br.com.zupacademy.rayllanderson.ecommerce.users.model.User;
import org.springframework.stereotype.Component;

@Component
public class EmailSenderFake implements EmailSender {
    @Override
    public void sendQuestionEmail(Question question) {
        User from = question.getUserWhoAsked();
        User to = question.getProduct().getOwner();
        String message = question.getTitle();
        String emailBody = "Ei, " + to.getUsername() + "! Você recebeu uma pergunta de "
                + from.getUsername() + "!" + "\nA Pergunta é:\n" + message;
        System.out.println(emailBody);
    }
}
