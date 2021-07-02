package br.com.zupacademy.rayllanderson.ecommerce.services.email;

import br.com.zupacademy.rayllanderson.ecommerce.orders.model.Order;
import br.com.zupacademy.rayllanderson.ecommerce.products.questions.model.Question;
import br.com.zupacademy.rayllanderson.ecommerce.transactions.tasks.TaskProcessor;
import br.com.zupacademy.rayllanderson.ecommerce.users.model.User;
import org.springframework.stereotype.Component;

@Component
public class EmailSenderFake implements EmailSender, TaskProcessor {
    @Override
    public void sendQuestionEmail(Question question) {
        User from = question.getUserWhoAsked();
        User to = question.getProduct().getOwner();
        String message = question.getTitle();
        String emailBody = "Ei, " + to.getUsername() + "! Você recebeu uma pergunta de "
                + from.getUsername() + "!" + "\nA Pergunta é:\n" + message;
        System.out.println(emailBody);
    }

    @Override
    public void sendOrderEmail(Order order) {
        User to = order.getSeller();
        System.out.println("Ei, " + to.getUsername() + "! Alguém quer comprar o produto " + order.getProductName() + "!\n" +
                "Faça o envio o mais rápido possível! Não deixe seu cliente esperando ;)");
    }

    @Override
    public void process(Order order) {
        String to = order.getBuyer().getUsername();
        String title = "Compra " + order.getId() + " foi aprovada com sucesso!";
        String details = "O seu produto " + order.getProductName() + " foi aprovado e logo mais será enviado :)";
        System.out.println(to);
        System.out.println(title);
        System.out.println(details);
    }
}
