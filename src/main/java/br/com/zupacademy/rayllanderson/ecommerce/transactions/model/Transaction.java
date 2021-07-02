package br.com.zupacademy.rayllanderson.ecommerce.transactions.model;

import br.com.zupacademy.rayllanderson.ecommerce.orders.model.Order;
import br.com.zupacademy.rayllanderson.ecommerce.transactions.enums.TransactionStatus;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Transaction {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank
    private String transactionId;
    @NotNull
    private TransactionStatus status;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Order order;
    @NotNull
    private LocalDateTime moment = LocalDateTime.now();

    @Deprecated
    private Transaction(){}

    public Transaction(String transactionId, TransactionStatus status, Order order) {
        this.transactionId = transactionId;
        this.status = status;
        this.order = order;
    }
}
