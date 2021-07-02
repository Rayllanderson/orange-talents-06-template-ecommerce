package br.com.zupacademy.rayllanderson.ecommerce.transactions.tasks;

import br.com.zupacademy.rayllanderson.ecommerce.orders.model.Order;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class TasksProcessors {

    private final Set<TaskProcessor> tasks;

    public TasksProcessors(Set<TaskProcessor> tasks) {
        this.tasks = tasks;
    }

    public void process(Order order){
        if(order.hasSuccessfullyProcessed()) {
            tasks.forEach(task -> task.process(order));
        } else {
            throw new IllegalStateException("Não foi ps");
        }
    }
}
