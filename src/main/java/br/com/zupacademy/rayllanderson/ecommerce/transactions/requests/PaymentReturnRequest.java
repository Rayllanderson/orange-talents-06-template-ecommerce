package br.com.zupacademy.rayllanderson.ecommerce.transactions.requests;

import br.com.zupacademy.rayllanderson.ecommerce.orders.model.Order;
import br.com.zupacademy.rayllanderson.ecommerce.transactions.model.Transaction;

public interface PaymentReturnRequest {

    Transaction toTransaction(Order order);
}
