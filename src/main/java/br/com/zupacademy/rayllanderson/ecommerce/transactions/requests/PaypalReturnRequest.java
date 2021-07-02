package br.com.zupacademy.rayllanderson.ecommerce.transactions.requests;

import br.com.zupacademy.rayllanderson.ecommerce.core.validation.annotations.UniqueValue;
import br.com.zupacademy.rayllanderson.ecommerce.orders.model.Order;
import br.com.zupacademy.rayllanderson.ecommerce.transactions.enums.TransactionStatus;
import br.com.zupacademy.rayllanderson.ecommerce.transactions.model.Transaction;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PaypalReturnRequest implements PaymentReturnRequest {

    @NotBlank
    @UniqueValue(domainClass = Transaction.class, field = "transactionId")
    private final String transactionId;

    @NotNull @Min(0) @Max(1)
    private final Integer status;

    public PaypalReturnRequest(@NotBlank String transactionId, @NotNull @Min(0) @Max(1) Integer status) {
        this.transactionId = transactionId;
        this.status = status;
    }

    @Override
    public Transaction toTransaction(Order order) {
        return new Transaction(transactionId, this.toTransactionStatus(), order);
    }

    private TransactionStatus toTransactionStatus(){
        return status == 1 ? TransactionStatus.SUCCESS : TransactionStatus.FAIL;
    }
}
