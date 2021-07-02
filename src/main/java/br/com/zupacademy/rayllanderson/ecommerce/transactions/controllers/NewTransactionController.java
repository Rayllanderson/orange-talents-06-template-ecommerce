package br.com.zupacademy.rayllanderson.ecommerce.transactions.controllers;

import br.com.zupacademy.rayllanderson.ecommerce.orders.model.Order;
import br.com.zupacademy.rayllanderson.ecommerce.transactions.model.Transaction;
import br.com.zupacademy.rayllanderson.ecommerce.transactions.requests.PagSeguroReturnRequest;
import br.com.zupacademy.rayllanderson.ecommerce.transactions.requests.PaymentReturnRequest;
import br.com.zupacademy.rayllanderson.ecommerce.transactions.requests.PaypalReturnRequest;
import br.com.zupacademy.rayllanderson.ecommerce.transactions.tasks.TasksProcessors;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.validation.Valid;

@RestController
public class NewTransactionController {

    private EntityManager manager;

    private final TasksProcessors tasksToBeProcessed;

    public NewTransactionController(EntityManager manager, TasksProcessors processors) {
        this.manager = manager;
        this.tasksToBeProcessed = processors;
    }

    @Transactional
    @PostMapping("/paypal-orders/{id}")
    public ResponseEntity<?> newPayPalTransaction(@PathVariable Long id, @RequestBody @Valid PaypalReturnRequest request){
        return process(id, request);
    }

    @Transactional
    @PostMapping("/pagseguro-orders/{id}")
    public ResponseEntity<?> newPagSeguroTransaction(@PathVariable Long id, @RequestBody @Valid PagSeguroReturnRequest request){
        return process(id, request);
    }

    private ResponseEntity<?> process(Long id, PaymentReturnRequest request){
        Order order = manager.find(Order.class, id);
        if (order.hasSuccessfullyProcessed()) return ResponseEntity.badRequest().body("Essa compra já foi finalizada");
        Transaction transaction = request.toTransaction(order);
        order.finish();
        this.manager.persist(transaction);
        tasksToBeProcessed.process(order);
        return ResponseEntity.ok().build();
    }
}
