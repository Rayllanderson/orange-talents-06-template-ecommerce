package br.com.zupacademy.rayllanderson.ecommerce.transactions.requests;

import br.com.zupacademy.rayllanderson.ecommerce.core.validation.annotations.UniqueValue;
import br.com.zupacademy.rayllanderson.ecommerce.orders.model.Order;
import br.com.zupacademy.rayllanderson.ecommerce.transactions.enums.PagSeguroReturnStatus;
import br.com.zupacademy.rayllanderson.ecommerce.transactions.enums.TransactionStatus;
import br.com.zupacademy.rayllanderson.ecommerce.transactions.model.Transaction;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PagSeguroReturnRequest implements PaymentReturnRequest{

    @NotBlank
    @UniqueValue(domainClass = Transaction.class, field = "transactionId")
    private final String transactionId;

    @NotNull
    private final PagSeguroReturnStatus status;

    public PagSeguroReturnRequest(String transactionId, PagSeguroReturnStatus status) {
        this.transactionId = transactionId;
        this.status = status;
    }

    @Override
    public Transaction toTransaction(Order order) {
        return new Transaction(transactionId, this.toTransactionalStatus(), order);
    }

    private TransactionStatus toTransactionalStatus(){
        return this.status.equals(PagSeguroReturnStatus.SUCESSO) ? TransactionStatus.SUCCESS : TransactionStatus.FAIL;
    }
}
