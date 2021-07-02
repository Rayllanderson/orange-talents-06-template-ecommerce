package br.com.zupacademy.rayllanderson.ecommerce.orders.controllers;

import br.com.zupacademy.rayllanderson.ecommerce.orders.model.Order;
import br.com.zupacademy.rayllanderson.ecommerce.orders.requests.OrderPostRequest;
import br.com.zupacademy.rayllanderson.ecommerce.products.model.Product;
import br.com.zupacademy.rayllanderson.ecommerce.services.email.EmailSender;
import br.com.zupacademy.rayllanderson.ecommerce.users.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.validation.Valid;

@RestController
@RequestMapping("/orders")
public class MakeOrderController {

    private EntityManager manager;
    private final EmailSender emailSender;

    public MakeOrderController(EntityManager manager, EmailSender emailSender) {
        this.manager = manager;
        this.emailSender = emailSender;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> makeAnOrder(@RequestBody @Valid OrderPostRequest request, @AuthenticationPrincipal User user,
                                         UriComponentsBuilder uriComponentsBuilder) {
        Product selectedProduct = manager.find(Product.class, request.getProductId());
        if (selectedProduct == null) return ResponseEntity.notFound().build();

        int quantity = request.getQuantity();
        boolean hasSuccessfullyReducedStock = selectedProduct.checkIfHasStockThenReduceFromStock(quantity);

        if(hasSuccessfullyReducedStock){
            Order order = new Order(quantity, selectedProduct, user, request.getGateway());

            manager.persist(order);
            emailSender.sendOrderEmail(order);

            return ResponseEntity.status(302).body(order.getReturnUrl(uriComponentsBuilder));
        }

        return ResponseEntity.badRequest().body("Não há estoque disponível para esta quantidade: " + quantity);
    }
}
